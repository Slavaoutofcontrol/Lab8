package gui;



import commands.RegistrateCommand;
import commands.ShowCommand;
import gui.actions.*;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import collectionClasses.*;
import connection.*;



import javax.swing.Timer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import static javax.swing.JOptionPane.*;


public class GuiManager {
    private final Client client;
    private static Locale locale = new Locale("en");
    private final ClassLoader classLoader = this.getClass().getClassLoader();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("GuiLabels", GuiManager.getLocale());
    private final JFrame frame;
    private Container contentPane;

    private Panel panel;
    private JTable table = null;
    private DefaultTableModel tableModel = null;
    private CartesianPanel cartesianPanel = null;
    private Object[][] tableData = null;
    private Collection<Movie> collection = null;
    private Map<JButton, String> buttonsToChangeLocale = new LinkedHashMap<>();
    private User user;
    private Sender sender;
    private final static Color RED_WARNING = Color.decode("#FF4040");
    private final static Color GREEN_OK = Color.decode("#00BD39");

    String[] columnNames = {"Id",
            "Name",
            "Coordinates",
            "CreationDate",
            "OscarCount",
            "MpaaRating",
            "Genre",
            "DirectorName",
            "DirectorWeight",
            "DirectorHairColour",
            "DirectorNationality",
            "Owner"
    };


    public GuiManager(Client client) {
        this.client = client;

        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.frame = new JFrame(resourceBundle.getString("lab8"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(this::run);
        this.sender = client.getSender();

    }

    public GuiManager(Client client, User user) {
        this.client = client;
        this.user = user;
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.frame = new JFrame(resourceBundle.getString("lab8"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(this::run);

    }

    public void run(){
        this.contentPane = this.frame.getContentPane();
        panel = new Panel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        if(user == null) this.loginAuth();
        frame.setJMenuBar(this.createMenuBar());

        JButton tableExecute = new JButton(resourceBundle.getString("Table"));
        JButton cartesianExecute = new JButton(resourceBundle.getString("Coordinates"));
        this.tableData = this.getTableData();
        this.tableModel = new DefaultTableModel(columnNames, tableData.length);
        this.tableModel.setDataVector(tableData, columnNames);
        this.table = new JTable(tableModel);

        new Timer(1000, (i) -> {
            Object[][] newTableData = this.getTableData();
            if (newTableData != null && !Arrays.deepEquals(this.tableData, newTableData)) {
                this.tableData = newTableData;
                this.tableModel.setRowCount(this.tableData.length + 1);
                this.tableModel.setDataVector(this.tableData, columnNames);
                this.tableModel.fireTableDataChanged();
                if (this.cartesianPanel != null) {
                    this.cartesianPanel.updateUserColors();
                    this.cartesianPanel.reanimate();
                }
            }
        }).start();

        TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
        this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Long id;
                try {
                    int row = table.convertRowIndexToModel(
                            table.getSelectedRow());
                    id = (Long) tableData[row][0];
                } catch (IndexOutOfBoundsException k) {return;}
                new UpdateAction(user, client, GuiManager.this).updateJOptionWorker(id);
            }
        });
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        //Компараторы
//        {
//            sorter.setComparator(2, Comparator.comparing(i -> ((Coordinates) i)));
//            sorter.setComparator(3, Comparator.comparing(
//                    i -> LocalDate.parse((String) i, dateFormat)));
//        }
        table.setRowSorter(sorter);


        JScrollPane tablePane = new JScrollPane(table);
        this.cartesianPanel = new CartesianPanel(client, user, this);
        JPanel cardPanel = new JPanel();
        JLabel userLabel = new JLabel(user.getLogin());
        userLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(tablePane, "Table");
        cardPanel.add(cartesianPanel, "Cartesian");

        tableExecute.addActionListener((actionEvent) -> {
            cardLayout.show(cardPanel, "Table");
        });
        cartesianExecute.addActionListener((actionEvent) -> {
            this.cartesianPanel.reanimate();
            cardLayout.show(cardPanel, "Cartesian");
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(cardPanel)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tableExecute)
                                .addComponent(cartesianExecute)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userLabel)
                                .addGap(5))));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(cardPanel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(tableExecute)
                        .addComponent(cartesianExecute)
                        .addComponent(userLabel)
                        .addGap(5)));
        frame.add(panel);
        frame.setVisible(true);
    }

    public Object[][] getTableData(){
        var showCommand = new ShowCommand();
        showCommand.setUser(this.user);
        Response response = sender.sendAndReceive(showCommand);
        if(response.getStatus() != ResponseStatus.OK) return null;
        this.collection = response.getCollection();
        return response.getCollection().stream()
                .map(this::createRow)
                .toArray(Object[][]::new);
    }

    private Object[] createRow(Movie movie){
        return new Object[]{
                movie.getId(),
                movie.getName(),
                movie.getCoordinates(),
                dateFormat.format(movie.getCreationDate()),
                movie.getOscarsCount(),
                movie.getMpaaRating(),
                movie.getGenre(),
                movie.getDirector().getName(),
                movie.getDirector().getWeight(),
                movie.getDirector().getHairColour(),
                movie.getDirector().getNationality(),
                movie.getUser_Login()
        };
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu actions = new JMenu(resourceBundle.getString("Actions"));
        JMenuItem add = new JMenuItem(resourceBundle.getString("Add"));
        JMenuItem addIfMin = new JMenuItem(resourceBundle.getString("AddIfMin"));
        JMenuItem addIfMax = new JMenuItem(resourceBundle.getString("AddIfMax"));
        JMenuItem countGreater = new JMenuItem(resourceBundle.getString("CountGreater"));
        JMenuItem countLess = new JMenuItem(resourceBundle.getString("CountLess"));
        JMenuItem groupCount = new JMenuItem(resourceBundle.getString("GroupCount"));
        JMenuItem clear = new JMenuItem(resourceBundle.getString("Clear"));
        JMenuItem executeScript = new JMenuItem(resourceBundle.getString("ExecuteScript"));
        JMenuItem exit = new JMenuItem(resourceBundle.getString("Exit"));
        JMenuItem info = new JMenuItem(resourceBundle.getString("Info"));
        JMenuItem remove = new JMenuItem(resourceBundle.getString("Remove"));
        JMenuItem removeGreater = new JMenuItem(resourceBundle.getString("RemoveGreater"));
        JMenuItem update = new JMenuItem(resourceBundle.getString("Update"));
        JMenuItem language = new JMenuItem(resourceBundle.getString("Language"));

        add.addActionListener(new AddAction(user, client, this));
        addIfMin.addActionListener(new AddIfMinAction(user, client, this));
        addIfMax.addActionListener(new AddIfMaxAction(user, client, this));
        countGreater.addActionListener(new CountGreaterThanGenreAction(user, client, this));
        countLess.addActionListener(new CountLessThanMpaaRatingAction(user, client, this));
        groupCount.addActionListener(new GroupCountingByDirectorAction(user, client, this));
        clear.addActionListener(new ClearAction(user, client, this));
        executeScript.addActionListener(new ExecuteScriptAction(user, client, this));
        exit.addActionListener(new ExitAction(user, client, this));
        info.addActionListener(new InfoAction(user, client, this));
        remove.addActionListener(new RemoveAction(user, client, this));
        removeGreater.addActionListener(new RemoveGreaterAction(user, client, this));
        update.addActionListener(new UpdateAction(user, client, this));
        language.addActionListener(new ChangeLanguageAction(user, client, this));

        actions.add(add);
        actions.addSeparator();
        actions.add(addIfMin);
        actions.addSeparator();
        actions.add(addIfMax);
        actions.addSeparator();
        actions.add(countGreater);
        actions.addSeparator();
        actions.add(countLess);
        actions.addSeparator();
        actions.add(groupCount);
        actions.addSeparator();
        actions.add(update);
        actions.addSeparator();
        actions.add(remove);
        actions.addSeparator();
        actions.add(removeGreater);
        actions.addSeparator();
        actions.add(clear);
        actions.addSeparator();
        actions.add(info);
        actions.addSeparator();
        actions.add(language);
        actions.addSeparator();
        actions.add(executeScript);
        actions.addSeparator();
        actions.add(exit);

        menuBar.add(actions);
        return menuBar;
    }

    public void loginAuth(){
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JLabel loginTextLabel = new JLabel(resourceBundle.getString("WriteLogin"));
        JTextField loginField = new JTextField();
        JLabel passwordTextLabel = new JLabel(resourceBundle.getString("EnterPass"));
        JPasswordField passwordField = new JPasswordField();
        JLabel errorLabel = new JLabel("");
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(loginTextLabel)
                        .addComponent(passwordTextLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(loginField)
                        .addComponent(passwordField)
                        .addComponent(errorLabel)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(loginTextLabel)
                        .addComponent(loginField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordTextLabel)
                        .addComponent(passwordField))
                .addComponent(errorLabel));
        while(true) {
            int result = JOptionPane.showOptionDialog(null, panel, resourceBundle.getString("Login"), JOptionPane.YES_NO_OPTION,
                    QUESTION_MESSAGE, null, new String[]{resourceBundle.getString("Login"), resourceBundle.getString("Register")}, resourceBundle.getString("Login"));
            if (result == OK_OPTION) {
                if (!checkFields(loginField, passwordField, errorLabel)) continue;
                var user = new User(loginField.getText(), passwordField.getText(), true);
                var regCommand = new RegistrateCommand(user);
                var request = new Request(regCommand);
                try {
                    sender.send(Serializer.serialize(request));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Response response = null;
                try {
                    response = sender.receive();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (response.getStatus() == ResponseStatus.OK) {
                    errorLabel.setText(resourceBundle.getString("LoginAcc"));
                    errorLabel.setForeground(GREEN_OK);
                    this.user = new User(loginField.getText(), String.valueOf(passwordField.getPassword()));
                    return;
                } else {
                    errorLabel.setText(resourceBundle.getString("LoginNotAcc"));
                    errorLabel.setForeground(RED_WARNING);
                }
            } else if (result == NO_OPTION){
                if (!checkFields(loginField, passwordField, errorLabel)) continue;
                var user = new User(loginField.getText(), passwordField.getText(), false);
                var regCommand = new RegistrateCommand(user);
                var request = new Request(regCommand);
                try {
                    sender.send(Serializer.serialize(request));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Response response = null;
                try {
                    response = sender.receive();
                } catch (IOException|ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (response.getStatus() == ResponseStatus.OK) {
                    errorLabel.setText(resourceBundle.getString("RegAcc"));
                    errorLabel.setForeground(GREEN_OK);
                    this.user = new User(loginField.getText(), String.valueOf(passwordField.getPassword()));
                    return;
                } else {
                    errorLabel.setText(resourceBundle.getString("RegNotAcc"));
                    errorLabel.setForeground(RED_WARNING);
                }
            } else if (result == CLOSED_OPTION) {
                System.exit(666);
            }
        }
    }

    private boolean checkFields(JTextField loginField, JPasswordField passwordField, JLabel errorLabel){
        if(loginField.getText().isEmpty()) {
            errorLabel.setText(resourceBundle.getString("LoginNotNull"));
            errorLabel.setForeground(RED_WARNING);
            return false;
        } else if(String.valueOf(passwordField.getPassword()).isEmpty()){
            errorLabel.setText(resourceBundle.getString("PassNotNull"));
            errorLabel.setForeground(RED_WARNING);
            return false;
        }
        return true;
    }

    public Collection<Movie> getCollection() {
        return collection;
    }

    public static Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        GuiManager.locale = locale;
        Locale.setDefault(locale);
        ResourceBundle.clearCache();
        resourceBundle = ResourceBundle.getBundle("GuiLabels", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
        this.buttonsToChangeLocale.forEach((i, j) -> i.setText(resourceBundle.getString(j)));
        this.tableData = this.getTableData();
        this.tableModel.setDataVector(this.tableData, columnNames);
        this.tableModel.fireTableDataChanged();
        this.frame.remove(panel);
        this.frame.setTitle(resourceBundle.getString("lab8"));
        this.run();
    }

}
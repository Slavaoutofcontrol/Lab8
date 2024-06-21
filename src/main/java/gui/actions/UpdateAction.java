package gui.actions;



import collectionClasses.*;
import commands.UpdateIdCommand;
import connection.*;
import gui.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class UpdateAction extends Action {
    LocalDate date = LocalDate.now();
    private Sender sender;

    public UpdateAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
        this.sender = client.getSender();
    }

    private Long getSelectedId() {
        Long[] userOwnedIds = guiManager.getCollection().stream()
                .filter((s) -> s.getUser_Login().equals(user.getLogin()))
                .map(Movie::getId)
                .toArray(Long[]::new);

        BorderLayout layout = new BorderLayout();
        JPanel panel = new JPanel(layout);
        JLabel question = new JLabel(resourceBundle.getString("SelectId"));
        JLabel idLabel = new JLabel(resourceBundle.getString("SelectId"));
        JComboBox idField = new JComboBox(userOwnedIds);

        layout.addLayoutComponent(question, BorderLayout.NORTH);
        layout.addLayoutComponent(idLabel, BorderLayout.WEST);
        layout.addLayoutComponent(idField, BorderLayout.EAST);

        JOptionPane.showMessageDialog(null,
                idField,
                resourceBundle.getString("Update"),
                JOptionPane.PLAIN_MESSAGE);
        return (Long) idField.getSelectedItem();
    }

    private Movie getObject(Long id) {
        return guiManager.getCollection().stream()
                .filter((s) -> s.getId().equals(id))
                .toList().get(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Long id = this.getSelectedId();
        updateJOptionWorker(id);
    }

    public void updateJOptionWorker(Long id) {
        if(id == null) JOptionPane.showMessageDialog(null, resourceBundle.getString("NoObjects"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);

        if(!guiManager.getCollection().stream()
                .filter((i) -> i.getId().equals(id))
                .toList()
                .get(0)
                .getUser_Login()
                .equals(user.getLogin())){
            JOptionPane.showMessageDialog(null,
                    resourceBundle.getString("ObjectNotYour"),
                    resourceBundle.getString("Error"),
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        JLabel mainLabel = new JLabel(resourceBundle.getString("MovieCreation"));
        JLabel nameLabel = new JLabel(resourceBundle.getString("Name"));
        JLabel cordXLabel = new JLabel(resourceBundle.getString("CoordinateX"));
        JLabel cordYLabel = new JLabel(resourceBundle.getString("CoordinateY"));
        JLabel oscarCountLabel = new JLabel(resourceBundle.getString("OscarCount"));
        JLabel ratingLabel = new JLabel(resourceBundle.getString("MpaaRating"));
        JLabel genreLabel = new JLabel(resourceBundle.getString("Genre"));
        JLabel dirNameLabel = new JLabel(resourceBundle.getString("DirectorName"));
        JLabel dirWeightLabel = new JLabel(resourceBundle.getString("DirectorWeight"));
        JLabel dirHairColourLabel = new JLabel(resourceBundle.getString("DirectorHairColour"));
        JLabel dirNationalityLabel = new JLabel(resourceBundle.getString("DirectorNationality"));
        JFormattedTextField nameField;
        JFormattedTextField cordXField;
        JFormattedTextField cordYField;
        JFormattedTextField oscarCountField;
        JComboBox MpaaRatingField;
        JComboBox GenreField;
        JFormattedTextField DirNameField;
        JFormattedTextField DirWeightField;
        JComboBox DirHairColourField;
        JComboBox DirNationalityField;
        // Action Listeners
        {
            nameField = new JFormattedTextField(new DefaultFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    if (text.trim().isEmpty()) {
                        throw new ParseException(resourceBundle.getString("FieldNotEmpty"), 0);
                    }
                    return super.stringToValue(text);
                }
            });
            cordXField = new JFormattedTextField(new DefaultFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    Integer num;
                    try {
                        num = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        throw new ParseException(resourceBundle.getString("NumberType") + " " + "integer", 0);
                    }
                    return num;
                }
            });
            cordYField = new JFormattedTextField(new DefaultFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    float num;
                    try {
                        num = Float.parseFloat(text);
                    } catch (NumberFormatException e) {
                        throw new ParseException(resourceBundle.getString("NumberType") + "float", 0);
                    }
                    if (num >= 214) throw new ParseException(resourceBundle.getString("NumberMustBe") + " " + resourceBundle.getString("less") + " 214", 0);
                    return num;
                }
            });
            oscarCountField = new JFormattedTextField(new DefaultFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    int num;
                    try {
                        num = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        throw new ParseException(resourceBundle.getString("NumberType") + " " + "int", 0);
                    }
                    if (num <= 0) throw new ParseException(resourceBundle.getString("NumberMustBe") + resourceBundle.getString("More") + " 0", 0);
                    return num;
                }
            });
            MpaaRatingField = new JComboBox(MpaaRating.values());
            GenreField = new JComboBox(MovieGenre.values());
            DirHairColourField = new JComboBox(Colour.values());
            DirNationalityField = new JComboBox(Country.values());
            DirNameField = new JFormattedTextField(new DefaultFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    if (text.trim().isEmpty()) {
                        throw new ParseException(resourceBundle.getString("FieldNotEmpty"), 0);
                    }
                    return super.stringToValue(text);
                }
            });
            DirWeightField = new JFormattedTextField(new DefaultFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    double num;
                    try {
                        num = Double.parseDouble(text);
                    } catch (NumberFormatException e) {
                        return resourceBundle.getString("NumberType") + " " + "double";
                    }
                    if (num <= 0) return resourceBundle.getString("NumberMustBe") + " " + resourceBundle.getString("More") + " 0";
                    return num;}
            });
        }
        // Group Layout
        {
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(mainLabel))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(nameLabel)
                            .addComponent(nameField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(cordXLabel)
                            .addComponent(cordXField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(cordYLabel)
                            .addComponent(cordYField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(oscarCountLabel)
                            .addComponent(oscarCountField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(ratingLabel)
                            .addComponent(MpaaRatingField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(genreLabel)
                            .addComponent(GenreField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(dirNameLabel)
                            .addComponent(DirNameField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(dirWeightLabel)
                            .addComponent(DirWeightField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(dirHairColourLabel)
                            .addComponent(DirHairColourField))
                    .addGroup(layout.createParallelGroup()
                            .addComponent(dirNationalityLabel)
                            .addComponent(DirNationalityField)))
                    ;
            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(mainLabel)
                            .addComponent(nameLabel)
                            .addComponent(cordXLabel)
                            .addComponent(cordYLabel)
                            .addComponent(oscarCountLabel)
                            .addComponent(ratingLabel)
                            .addComponent(genreLabel)
                            .addComponent(dirNameLabel)
                            .addComponent(dirWeightLabel)
                            .addComponent(dirHairColourLabel)
                            .addComponent(dirNationalityLabel)
                    )
                    .addGroup(layout.createParallelGroup()
                            .addComponent(nameField)
                            .addComponent(cordXField)
                            .addComponent(cordYField)
                            .addComponent(oscarCountField)
                            .addComponent(MpaaRatingField)
                            .addComponent(GenreField)
                            .addComponent(DirNameField)
                            .addComponent(DirWeightField)
                            .addComponent(DirHairColourField)
                            .addComponent(DirNationalityField)
                    ));
        }
        int result = JOptionPane.showOptionDialog(null, panel, resourceBundle.getString("Update"), JOptionPane.YES_OPTION,
                QUESTION_MESSAGE, null, new String[]{resourceBundle.getString("Update")}, resourceBundle.getString("Update"));
        if(result == OK_OPTION){
            Movie newMovie = new Movie(
                    nameField.getText(),
                    new Coordinates(
                            Integer.parseInt(cordXField.getText()),
                            Float.parseFloat(cordYField.getText())
                    ),
                    date,
                    Integer.parseInt(oscarCountField.getText()),
                    (MpaaRating) MpaaRatingField.getSelectedItem(),
                    (MovieGenre) GenreField.getSelectedItem(),
                    new Person(
                            DirNameField.getText(),
                            Double.parseDouble(DirWeightField.getText()),
                            (Colour) DirHairColourField.getSelectedItem(),
                            (Country) DirNationalityField.getSelectedItem()
                    ),
                    user.getLogin()
            );
            if(!newMovie.validate()) {
                JOptionPane.showMessageDialog(null, resourceBundle.getString("ObjectNotValid"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
                return;
            }
            newMovie.setId(id);
            var update = new UpdateIdCommand(newMovie);
            update.setUser(user);
            var response = sender.sendAndReceive(update);
            if(response.getStatus() == ResponseStatus.OK) JOptionPane.showMessageDialog(null, resourceBundle.getString("ObjectUpdated"), resourceBundle.getString("Ok"), JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(null, resourceBundle.getString("ObjectNotChanged"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
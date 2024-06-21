package gui.actions;


import collectionClasses.*;
import commands.AddCommand;
import commands.AddIfMinCommand;
import connection.*;
import gui.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class AddIfMinAction extends Action {
    LocalDate date = LocalDate.now();
    private Sender sender;

    public AddIfMinAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
        this.sender = client.getSender();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                        throw new ParseException(resourceBundle.getString("NumberType") + " " + "double", 0);
                    }
                    if (num <= 0) throw new ParseException(resourceBundle.getString("NumberMustBe") + resourceBundle.getString("More") + " 0", 0);
                    return num;
                }
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
        int result = JOptionPane.showOptionDialog(null, panel, resourceBundle.getString("Add"), JOptionPane.YES_OPTION,
                QUESTION_MESSAGE, null, new String[]{resourceBundle.getString("Add")}, resourceBundle.getString("Add"));
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
            var add = new AddIfMinCommand(newMovie);
            add.setUser(user);
            var response = sender.sendAndReceive(add);
            if(response.getStatus() == ResponseStatus.OK) JOptionPane.showMessageDialog(null, resourceBundle.getString("ObjectAcc"), resourceBundle.getString("Result"), JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(null, resourceBundle.getString("ObjectNotValid"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}

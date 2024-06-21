package gui.actions;




import commands.*;

import collectionClasses.*;
import commands.AddCommand;
import connection.*;
import gui.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;

import static javax.swing.JOptionPane.*;

public class CountGreaterThanGenreAction extends Action{
    private Sender sender;
    public CountGreaterThanGenreAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
        this.sender = client.getSender();
    }
    private MovieGenre getSelectedGenre() {

        BorderLayout layout = new BorderLayout();
        JPanel panel = new JPanel(layout);
        JLabel question = new JLabel(resourceBundle.getString("SelectGenre"));
        JLabel genreLabel = new JLabel(resourceBundle.getString("Genre"));
        JComboBox GenreField;
        GenreField = new JComboBox(MovieGenre.values());

        layout.addLayoutComponent(question, BorderLayout.NORTH);
        layout.addLayoutComponent(genreLabel, BorderLayout.WEST);
        layout.addLayoutComponent(GenreField, BorderLayout.EAST);

        JOptionPane.showMessageDialog(null,
                GenreField,
                resourceBundle.getString("FindOut"),
                JOptionPane.PLAIN_MESSAGE);
        return (MovieGenre) GenreField.getSelectedItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {MovieGenre genre = this.getSelectedGenre();
        if(genre == null) JOptionPane.showMessageDialog(null, resourceBundle.getString("NoObjects"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
        var count = new CountGreaterThanGenreCommand(genre);
        count.setUser(user);
        var response = sender.sendAndReceive(count);
        if(response.getStatus() == ResponseStatus.OK) {
            JOptionPane.showMessageDialog(null, resourceBundle.getString("Genremore") + " " + response.getResponse(), resourceBundle.getString("Result"), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
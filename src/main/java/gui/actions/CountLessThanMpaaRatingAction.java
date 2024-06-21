package gui.actions;




import commands.*;

import collectionClasses.*;
import connection.*;
import gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CountLessThanMpaaRatingAction extends Action{
    private Sender sender;
    public CountLessThanMpaaRatingAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
        this.sender = client.getSender();
    }
    private MpaaRating getSelectedRating() {

        BorderLayout layout = new BorderLayout();
        JPanel panel = new JPanel(layout);
        JLabel question = new JLabel(resourceBundle.getString("SelectMpaaRating"));
        JLabel mpaaRatingLabel = new JLabel(resourceBundle.getString("MpaaRating"));
        JComboBox MpaaRatingField;
        MpaaRatingField = new JComboBox(MpaaRating.values());

        layout.addLayoutComponent(question, BorderLayout.NORTH);
        layout.addLayoutComponent(mpaaRatingLabel, BorderLayout.WEST);
        layout.addLayoutComponent(MpaaRatingField, BorderLayout.EAST);

        JOptionPane.showMessageDialog(null,
                MpaaRatingField,
                resourceBundle.getString("FindOut"),
                JOptionPane.PLAIN_MESSAGE);
        return (MpaaRating) MpaaRatingField.getSelectedItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {MpaaRating rating = this.getSelectedRating();
        if(rating== null) JOptionPane.showMessageDialog(null, resourceBundle.getString("NoObjects"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
        var count = new CountLessThanMpaaRatingCommand(rating);
        count.setUser(user);
        var response = sender.sendAndReceive(count);
        if(response.getStatus() == ResponseStatus.OK) {
            JOptionPane.showMessageDialog(null, resourceBundle.getString("MpaaRatingless") +" "+ response.getResponse(), resourceBundle.getString("Result"), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
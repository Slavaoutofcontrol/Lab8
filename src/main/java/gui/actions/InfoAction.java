package gui.actions;




import commands.InfoCommand;
import commands.UpdateIdCommand;
import connection.*;
import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoAction extends Action{
    private Sender sender;

    public InfoAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
        this.sender = client.getSender();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var info = new InfoCommand();
        info.setUser(Client.getUser());
        var response = sender.sendAndReceive(info);
        if(response.getStatus() == ResponseStatus.OK) JOptionPane.showMessageDialog(null, response.getResponse(), resourceBundle.getString("Result"), JOptionPane.PLAIN_MESSAGE);
        else JOptionPane.showMessageDialog(null, resourceBundle.getString("NoResult"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
    }
}
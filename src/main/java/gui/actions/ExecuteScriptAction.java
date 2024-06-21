package gui.actions;



import commands.AddCommand;
import commands.ExecuteScriptCommand;
import connection.Client;
import connection.ResponseStatus;
import connection.Sender;
import connection.User;
import console.Console;
import gui.*;
import utils.ExecuteFileManager;
import utils.ScriptExecutor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static javax.swing.JOptionPane.*;

public class ExecuteScriptAction extends Action{
    private Sender sender;
    public ExecuteScriptAction(User user, Client client, GuiManager guiManager) {
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
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        JLabel fileAsker = new JLabel(resourceBundle.getString("SelectFile"));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(fileAsker)
                        .addComponent(fileChooser)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(fileAsker)
                .addComponent(fileChooser));

        int option = JOptionPane.showOptionDialog(null,
                panel,
                resourceBundle.getString("ScriptExecute"),
                JOptionPane.YES_NO_OPTION,
                QUESTION_MESSAGE,
                null,
                new String[]{resourceBundle.getString("Yes"), resourceBundle.getString("No")},
                resourceBundle.getString("Yes"));
        if(option == OK_OPTION) {
            ScriptExecutor scriptex = new ScriptExecutor(new File(fileChooser.getSelectedFile().getAbsolutePath()));
            var script = new ExecuteScriptCommand(scriptex.getCommandList());
            script.setUser(user);
            var response = sender.sendAndReceive(script);
            if(response.getStatus() == ResponseStatus.OK) JOptionPane.showMessageDialog(null, resourceBundle.getString("ExScriptDone"), resourceBundle.getString("Result"), JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(null, resourceBundle.getString("ObjectNotValid"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
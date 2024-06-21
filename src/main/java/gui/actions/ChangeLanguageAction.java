package gui.actions;


import connection.*;
import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Locale;

public class ChangeLanguageAction extends Action{

    public ChangeLanguageAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox languages = new JComboBox(new Object[]{
                new Locale("ru"),
                new Locale("est"),
                new Locale("hu"),
                new Locale("es", "CO")
        });
        JOptionPane.showMessageDialog(null,
                languages,
                "Choose language",
                JOptionPane.INFORMATION_MESSAGE);
        guiManager.setLocale((Locale) languages.getSelectedItem());
    }
}
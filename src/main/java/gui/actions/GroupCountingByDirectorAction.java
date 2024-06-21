package gui.actions;



import commands.GroupCountingByDirectorCommand;
import connection.*;
import gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupCountingByDirectorAction extends Action{
    private Sender sender;
    public GroupCountingByDirectorAction(User user, Client client, GuiManager guiManager) {
        super(user, client, guiManager);
        this.sender = client.getSender();
    }

    public static Map<String, List<String>> parseStringToMap(String input) {
        Map<String, List<String>> resultMap = new HashMap<>();

        // Паттерн для поиска ключей и значений
        Pattern pattern = Pattern.compile("\"(.*?)\": \\[(.*?)\\]");
        Matcher matcher = pattern.matcher(input);

        // Парсинг и добавление в resultMap
        while (matcher.find()) {
            String key = matcher.group(1);
            String[] values = matcher.group(2).split(", ");
            List<String> valueList = new ArrayList<>();
            for (String value : values) {
                valueList.add(value.replace("\"", ""));
            }
            resultMap.put(key, valueList);
        }

        return resultMap;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        var count = new GroupCountingByDirectorCommand();
        count.setUser(user);
        var response = sender.sendAndReceive(count);
        if (response.getStatus() == ResponseStatus.OK) {
            StringBuilder resultOutput = new StringBuilder();

            // Пример формирования строки вывода
            Map<String, List<String>> resultMap = parseStringToMap(response.getResponse());
            for(Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
                resultOutput.append(resourceBundle.getString("Director")).append(" ").append(entry.getKey()).append(" :\n");
                List<String> movies = entry.getValue();
                for(int i = 0; i < movies.size(); i++) {
                    resultOutput.append(" -").append(movies.get(i)).append("\n");
                }
            }

            JOptionPane.showMessageDialog(null, resultOutput.toString(), resourceBundle.getString("Result"), JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resourceBundle.getString("NoResult"), resourceBundle.getString("Error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}

package utils;

import collectionClasses.CommandType;
import collectionClasses.Movie;
import collectionClasses.MovieCreator;
import commands.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class ScriptExecutor {
    private final ArrayList<Command> commandQueue = new ArrayList<>();
    private final File scriptFile;
    private final ArrayDeque<File> fileMemory = new ArrayDeque<>();
    public ScriptExecutor(File scriptFile){
        this.scriptFile = scriptFile;
    }
    public ArrayList<Command> getCommandList() {
        return commandQueue;
    }
    private ScriptExecutor readScript(File scriptFile) {
        List<String> lines;
        try {
            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Невозможно исполнить скрипт:" + ((!scriptFile.exists()) ? " файл не найден" : " нет прав на чтение файла"));
            return this;
        }
        fileMemory.add(scriptFile);
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            CommandType commandType = CommandUtils.getCommandType(line.split(" ")[0]);
            String[] args = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);
            Command cmd = null;
            if (commandType == CommandType.EXECUTE_SCRIPT) {
                if (fileMemory.contains(new File(args[0]))) {
                    System.err.println("Обнаружена рекурсия, строка пропущена");
                    continue;
                }
                if (FileUtil.isFileExist(args[0])) {
                    this.readScript(new File(args[0]));
                    continue;
                }
            }
            if (Set.of(CommandType.UPDATE_ID, CommandType.REMOVE_GREATER).contains(commandType)){
                if (args.length < 1 || index + 10 >= lines.size() || args.length > 1) {
                    System.out.println("Неверное количество аргументов для команды " + commandType + ". Строка пропущена");
                    continue;
                }
                String[] movieArgs = lines.subList(index + 1, index + 11).toArray(new String[0]);
                args = new String[movieArgs.length + 1];
                args[0] = line.split(" ")[1];
                System.arraycopy(movieArgs, 0, args, 1, movieArgs.length);
                index += 10;
                Movie movie = MovieCreator.createMovie(movieArgs);
                if (movie != null) {
                    try{
                        movie.setId(Integer.parseInt(args[0]));
                    } catch (NumberFormatException e){
                        System.out.println("Неверные аргументы для команды " + commandType + ". Команда пропущена");
                        continue;
                    }
                    if (commandType == CommandType.UPDATE_ID){
                        cmd = new UpdateIdCommand(movie);
                    } else {
                        cmd = new RemoveGreaterCommand(movie);
                    }
                } else {
                    System.out.println("Неверные аргументы для команды " + commandType + ". Команда пропущена");
                    continue;
                }
            } else if (Set.of(CommandType.ADD, CommandType.ADD_IF_MIN, CommandType.ADD_IF_MAX).contains(commandType)) {
                if (args.length != 0 || index + 10 >= lines.size()){
                    System.out.println("Неверное количество аргументов или неверные аргументы для команды " + commandType + ". Строка пропущена");
                    continue;
                }
                String[] movieArgs = lines.subList(index + 1, index + 11).toArray(new String[0]);
                index += 10;
                Movie movie = MovieCreator.createMovie(movieArgs);
                if (movie != null){
                    switch (commandType){
                        case ADD -> cmd = new AddCommand(movie);
                        case ADD_IF_MAX -> cmd = new AddIfMaxCommand(movie);
                        case ADD_IF_MIN -> cmd = new AddIfMinCommand(movie);
                    }
                } else {
                    System.out.println("Неверное количество аргументов или неверные аргументы для команды " + commandType + ". Строка пропущена");
                    continue;
                }
            } else {
                cmd = CommandFactory.createCommand(commandType, line.split(" "));
            }
            if (cmd != null) commandQueue.add(cmd);
        }
        fileMemory.pop();
        return this;
    }
    public ScriptExecutor readScript() {
        return this.readScript(scriptFile);
    }
}

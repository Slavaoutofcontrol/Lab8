package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;
import java.util.ArrayList;


public class ExecuteScriptCommand implements Command {

    @Serial
    private static final long serialVersionUID = -7431356007880416793L;
    private ArrayList<Command> commands;
    private User user;

    public ExecuteScriptCommand(ArrayList<Command> commands) {
        this.commands = commands;
    }
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Movie getMovie() {
        return null;
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }

}
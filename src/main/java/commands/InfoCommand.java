package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class InfoCommand implements Command{

    @Serial
    private static final long serialVersionUID = -6035633453588208723L;
    private User user;
    public InfoCommand(){}
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

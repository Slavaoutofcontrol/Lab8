package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class ExitCommand implements Command{

    @Serial
    private static final long serialVersionUID = 7239456687167239029L;
    private User user;
    public ExitCommand(){
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
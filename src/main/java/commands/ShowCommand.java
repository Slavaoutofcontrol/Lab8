package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class ShowCommand implements Command{

    @Serial
    private static final long serialVersionUID = 2237136524858086694L;
    private User user;
    public ShowCommand(){}
    @Override
    public void setUser(User user) {
        this.user= user;
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

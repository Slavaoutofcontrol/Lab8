package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class HelpCommand implements Command {

    @Serial
    private static final long serialVersionUID = 4831338180725440541L;
    private User user;
    public HelpCommand(){
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

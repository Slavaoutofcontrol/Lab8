package commands;

import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class DefaultCommand implements Command{
    @Serial
    private static final long serialVersionUID = -4745382075765516610L;
    private User user;
    public DefaultCommand() {
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

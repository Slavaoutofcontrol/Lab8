package commands;

import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class RegistrateCommand implements Command {
    @Serial
    private static final long serialVersionUID = 4884185358828753678L;

    private User user;
    public RegistrateCommand(User user){
        this.user = user;
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

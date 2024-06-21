package commands;



import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class ClearCommand implements Command{

    @Serial
    private static final long serialVersionUID = -8925036261411386007L;
    private User user;
    public ClearCommand() {
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

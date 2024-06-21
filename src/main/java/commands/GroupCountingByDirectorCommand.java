package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class GroupCountingByDirectorCommand implements Command{

    @Serial
    private static final long serialVersionUID = -8936229594545431248L;
    private User user;
    public GroupCountingByDirectorCommand(){}
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
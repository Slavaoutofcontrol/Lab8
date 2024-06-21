package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class RemoveByIdCommand implements Command{

    @Serial
    private static final long serialVersionUID = 904823743523031940L;
    private long id;
    private User user;
    public RemoveByIdCommand(Long id) {
        this.id = id;
    }
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

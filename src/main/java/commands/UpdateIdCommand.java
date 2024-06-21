package commands;



import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class UpdateIdCommand implements Command{

    @Serial
    private static final long serialVersionUID = -620855360289409781L;
    private Movie movie;
    private User user;

    public UpdateIdCommand(Movie movie) {
        this.movie = movie;
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

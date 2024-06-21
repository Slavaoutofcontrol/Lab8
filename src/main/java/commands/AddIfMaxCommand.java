package commands;

import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class AddIfMaxCommand implements Command{

    @Serial
    private static final long serialVersionUID = -11081439549547967L;
    private Movie movie;
    private User user;
    public AddIfMaxCommand(Movie movie) {
        this.movie = movie;
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
        return movie;
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }
}

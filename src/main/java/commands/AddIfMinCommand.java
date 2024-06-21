package commands;

import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class AddIfMinCommand implements Command{

    @Serial
    private static final long serialVersionUID = -4252936670992812458L;
    private Movie movie;
    private User user;
    public AddIfMinCommand(Movie movie) {
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

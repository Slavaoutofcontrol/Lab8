package commands;


import collectionClasses.Movie;
import connection.User;

import java.io.Serial;


public class AddCommand implements Command{

    @Serial
    private static final long serialVersionUID = 4884185358828753717L;
    private Movie movie;
    private User user;
    public AddCommand(Movie movie) {
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

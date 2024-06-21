package commands;

import collectionClasses.Movie;
import connection.User;

import java.io.Serial;

public class RemoveGreaterCommand implements Command{

    @Serial
    private static final long serialVersionUID = -6679190409592074342L;
    private Movie movie;
    private User user;
    public RemoveGreaterCommand(Movie movie){
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
        return null;
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }
}
package commands;

import collectionClasses.Movie;
import collectionClasses.MovieGenre;
import connection.User;

import java.io.Serial;

public class CountGreaterThanGenreCommand implements Command{

    @Serial
    private static final long serialVersionUID = -2442949581870811398L;
    private MovieGenre genre;
    private User user;
    public CountGreaterThanGenreCommand(MovieGenre genre){
        this.genre = genre;
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

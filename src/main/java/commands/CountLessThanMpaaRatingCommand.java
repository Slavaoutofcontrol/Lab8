package commands;

import collectionClasses.Movie;
import collectionClasses.MpaaRating;
import connection.User;

import java.io.Serial;

public class CountLessThanMpaaRatingCommand implements Command{

    @Serial
    private static final long serialVersionUID = -246712803353855092L;
    private MpaaRating mpaaRating;
    private User user;
    public CountLessThanMpaaRatingCommand(MpaaRating mpaaRating){
        this.mpaaRating = mpaaRating;
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

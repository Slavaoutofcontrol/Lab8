package commands;
import collectionClasses.Movie;
import connection.User;

import java.io.Serializable;
public interface Command extends Serializable{
    void setUser(User user);
    User getUser();
    Movie getMovie();
    Integer getIntArgument();
}

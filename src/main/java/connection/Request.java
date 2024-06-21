package connection;

import collectionClasses.Movie;
import commands.Command;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 1212287487663584545L;
    private Command command;
    private String args = "";
    private Movie movie = null;
    private User user;
    public  Request(Command command){
        this.command = command;
        this.user = command.getUser();
        this.args = command.getIntArgument() == null ? "" : command.getIntArgument().toString();
        this.movie = command.getMovie();
    }
    public Request(ResponseStatus responseStatus, Command commandName, Movie movie){
        this.command = commandName;
    }
    public Request(Command commandName, String args){
        this.command = commandName;
        this.args = args.trim();
    }
    public Request(Command commandName, Movie movie){
        this.command = commandName;
        this.movie = movie;
    }
    public Request(Command commandName, String args, Movie movie){
        this.command = commandName;
        this.args = args.trim();
        this.movie = movie;
    }
    public boolean isEmpty(){
        return command == null && args.isEmpty() && movie == null;
    }
    public Command getCommandName(){
        return command;
    }
    public String getArgs() {
        return args;
    }
    public Movie getMovie() {
        return movie;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(command, request.command) && Objects.equals(args, request.args) && Objects.equals(movie, request.movie);
    }
    @Override
    public int hashCode(){
        return Objects.hash(command, args, movie);
    }
    @Override
    public String toString(){
        return "Request[" + command.toString() +
                (args.isEmpty()
                        ? ""
                        : ", " + args) +
                ((movie == null)
                        ? "]"
                        : ", " + movie + "]");
    }
}

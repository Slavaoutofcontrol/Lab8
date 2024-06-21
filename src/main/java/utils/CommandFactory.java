package utils;

import collectionClasses.CommandType;
import collectionClasses.Movie;
import collectionClasses.MovieGenre;
import collectionClasses.MpaaRating;
import commands.*;
import connection.User;
import console.Console;
import forms.MovieForm;
import forms.UserForm;

import java.util.Arrays;

/**
 * Class {@code CommandFactory} для создания объекта команды
 */
public class CommandFactory {
    /**
     * Метод создающий объект команды
     * @param type типы введенной команды
     * @param userInput пользовательский ввод
     * @return объект
     * @see Command
     * @see CommandType
     */
    public static Command createCommand(CommandType type, String[] userInput) {
        String[] args;
        if (userInput.length == 0) {
            args = new String[]{};
        } else {
            args = Arrays.copyOfRange(userInput, 1, userInput.length);
        }
        return switch (type) {
            case EXIT -> {
                System.out.println("Ciao!");
                System.exit(0);
                yield null;
            }
            case CLEAR -> new ClearCommand();
            case HELP -> new HelpCommand();
            case INFO -> new InfoCommand();
            case GROUP_COUNTING_BY_DIRECTOR -> new GroupCountingByDirectorCommand();
            case SHOW -> new ShowCommand();
            case REMOVE_BY_ID -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    yield new RemoveByIdCommand(Long.parseLong(args[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Аргумент должен быть целым числом. ");
                    yield null;
                }
            }
            case COUNT_GREATER_THAN_GENRE -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                    yield new CountGreaterThanGenreCommand(MovieGenre.valueOf(args[0].toUpperCase()));
            }
            case COUNT_LESS_THAN_MPAA_RATING -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                    yield new CountLessThanMpaaRatingCommand(MpaaRating.valueOf(args[0].toUpperCase()));
            }
            case UPDATE_ID -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    long id = Long.parseLong(args[0]);
                    Movie movie;
                    MovieForm movieForm = new MovieForm(new Console());
                    movie = movieForm.build();
                    movie.setId(id);
                    if (movie != null) yield new UpdateIdCommand(movie);
                    yield null;
                } catch (NumberFormatException e) {
                    System.out.println("id должен быть целым положительным числом.");
                    yield null;
                }
            }
            case ADD -> {
                Movie movie;
                MovieForm movieForm = new MovieForm(new Console());
                movie = movieForm.build();
                yield new AddCommand(movie);
            }
            case ADD_IF_MAX -> {
                Movie movie;
                MovieForm movieForm = new MovieForm(new Console());
                movie = movieForm.build();
                yield new AddIfMaxCommand(movie);
            }
            case ADD_IF_MIN -> {
                Movie movie;
                MovieForm movieForm = new MovieForm(new Console());
                movie = movieForm.build();
                yield new AddIfMinCommand(movie);

            }
            case REMOVE_GREATER -> {
                Movie movie;
                MovieForm movieForm = new MovieForm(new Console());
                movie = movieForm.build();
                if (movie != null) yield new RemoveGreaterCommand(movie);
                yield null;
            }
            case REGISTRATE-> {
                User user;
                UserForm userForm = new UserForm(new Console());
                user = userForm.build();
                if (user != null) yield new RegistrateCommand(user);
                yield null;
            }
            default -> {
                System.out.println("Неизвестная команда: " + userInput[0]);
                yield null;
            }
        };
    }
}
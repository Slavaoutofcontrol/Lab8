package forms;


import collectionClasses.MovieGenre;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code MovieGenreForm} для создания поля жанра
 * @see Form
 * @see MovieGenre
 */
public class MovieGenreForm extends Form<MovieGenre> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public MovieGenreForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public MovieGenre build() {
        ReadManager readManager = new ReadManager(console);
        return readManager.readMovieGenre();
    }
}
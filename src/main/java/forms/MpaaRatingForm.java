package forms;


import collectionClasses.MpaaRating;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code MpaaRatingForm} для создания поля возрастное ограничение
 * @see Form
 * @see MpaaRating
 */
public class MpaaRatingForm extends Form<MpaaRating> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public MpaaRatingForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public MpaaRating build() {
        ReadManager readManager = new ReadManager(console);
        return readManager.readMpaaRating();
    }
}

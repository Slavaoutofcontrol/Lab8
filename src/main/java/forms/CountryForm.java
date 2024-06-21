package forms;


import collectionClasses.Country;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code CountryForm} для создания поля национальность
 * @see Form
 * @see Country
 */
public class CountryForm extends Form<Country> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public CountryForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public Country build() {
        ReadManager readManager = new ReadManager(console);
        return readManager.readNationality();
    }
}
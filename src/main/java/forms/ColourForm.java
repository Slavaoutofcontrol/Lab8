package forms;


import collectionClasses.Colour;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code ColourForm} для создания поля цвет волос
 * @see Form
 * @see Colour
 */
public class ColourForm extends Form<Colour> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public ColourForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public Colour build() {
        ReadManager readManager = new ReadManager(console);
        return readManager.readHairColour();
    }
}
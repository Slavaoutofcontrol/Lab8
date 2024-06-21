package forms;

import collectionClasses.Colour;
import collectionClasses.Country;
import collectionClasses.Person;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code PersonForm} для создания режиссера
 * @see Form
 * @see Person
 */

public class PersonForm extends Form<Person> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public PersonForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public Person build() {
        ReadManager readManager = new ReadManager(console);
        return new Person(
                readManager.readDirectorsName(),
                readManager.readDirectorsWeight(),
                readHairColour(),
                readNationality()
        );
    }
    private Colour readHairColour() {
        return new ColourForm(console).build();
    }
    private Country readNationality() {
        return new CountryForm(console).build();
    }
}

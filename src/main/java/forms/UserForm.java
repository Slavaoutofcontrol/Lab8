package forms;

import connection.User;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

public class UserForm extends Form{
    private final ReaderWriter console;
    private final UserInput scanner;
    public UserForm(ReaderWriter console){
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    @Override
    public User build() {
        ReadManager readManager = new ReadManager(console);
        console.write("У вас есть регистрация?");
        while (true){
            console.write("Введите yes/y или no/n: ");
            String answer = console.readLine().toLowerCase();
            if (!(answer.equals("yes")||answer.equals("y") ||answer.equals("no") || answer.equals("n"))) {
                System.out.println("Неверный ответ. Введите yes/y, если зарегистрированы, или no/n, если нет");
            } else {
                return new User(
                        readManager.readLogin(),
                        readManager.readPassword(),
                        answer.equals("yes")||answer.equals("y")
                );
            }
        }
    }
}

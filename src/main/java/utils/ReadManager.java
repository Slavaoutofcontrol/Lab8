package utils;

import collectionClasses.Colour;
import collectionClasses.Country;
import collectionClasses.MovieGenre;
import collectionClasses.MpaaRating;
import console.*;

import java.util.Arrays;

/**
 * Class {@code ReadManager} считывает поля фильма из консоли
 * @see Readable
 */
public class ReadManager implements Readable {
    private final ReaderWriter console;
    private final UserInput scanner;
    public ReadManager(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    /**
     * @return имя фильма
     */
    @Override
    public String readName(){
        String name;
        while (true) {
            console.write("Введите название фильма:");
            name = console.readLine();
            if (name.isEmpty() || name.isBlank()) {
                console.printError("Имя не может быть пустой строкой");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return name;
            }
        }
    }

    /**
     * @return координату x
     */
    @Override
    public Integer readCoordinateX(){
        while (true){
            console.write("Введите координату X: ");
            try{
                Integer coordinateX = console.readInt();
                if( coordinateX == null){
                }else{
                    return coordinateX;
                }
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return координату y
     */
    @Override
    public Float readCoordinateY(){
        while (true){
            console.write("Введите координату Y: ");
            try{
                float coordinateY = console.readFloat();
                if( coordinateY >= 214){
                    console.printError("Координата Y должна быть меньше 214: ");
                }else{
                    return coordinateY;
                }
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }
    /**
     * @return кол-во оскаров
     */
    @Override
    public Integer readOscarCount(){
        while (true){
            console.write("Введите количество  оскаров: ");
            try{
                int oscarCount = console.readInt();
                if(oscarCount > 0){
                    return oscarCount;
                }else{
                    console.printError("Количество оскаров должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return жанр
     */
    @Override
    public MovieGenre readMovieGenre(){
        console.write("Вы должны ввести один из перечисленных жанров: " + Arrays.toString(MovieGenre.values()));
        while (true){
            try{
                return MovieGenre.valueOf(console.getValidatedValue("\nВведите жанр: ").toUpperCase());
            }catch (IllegalArgumentException e){
                console.printError("Жанр введён неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return возрастное ограничение
     */
    @Override
    public MpaaRating readMpaaRating(){
        console.write("Вы должны ввести один из перечисленных возрастных рейтингов: " + Arrays.toString(MpaaRating.values()));
        while (true){
            try{
                return MpaaRating.valueOf(console.getValidatedValue("\nВведите возрастной рейтинг: ").toUpperCase());
            }catch (IllegalArgumentException e){
                console.printError("Возрастной рейтинг введён неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return имя режиссера
     */
    @Override
    public String readDirectorsName(){
        String directorsname;
        while (true) {
            console.write("Введите имя режиссера:");
            directorsname = console.readLine();
            if (directorsname.isEmpty() || directorsname.isBlank()) {
                console.printError("Имя не может быть пустой строкой");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return directorsname;
            }
        }
    }

    /**
     * @return вес режиссера
     */
    @Override
    public Double readDirectorsWeight(){
        while (true){
            console.write("Внимание! Очень важная информация!\n");
            console.write("Введите вес режиссера: ");
            try{
                double weight = console.readDouble();
                if(weight > 0){
                    return weight;
                }else{
                    console.printError("Вес должен быть положительным числом. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return цвет волос режиссера
     */
    @Override
    public Colour readHairColour(){
        console.write("Вы должны ввести один из перечисленных цветов волос: " + Arrays.toString(Colour.values()));
        while (true){
            try{
                return Colour.valueOf(console.getValidatedValue("\nВведите цвет волос: ").toUpperCase());
            }catch (IllegalArgumentException e){
                console.printError("Цвет волос введён неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return национальность режиссера
     */
    @Override
    public Country readNationality(){
        console.write("Вы должны ввести один из перечисленных национальностей : " + Arrays.toString(Country.values()));
        while (true){
            try{
                return Country.valueOf(console.getValidatedValue("\nВведите национальность: ").toUpperCase());
            }catch (IllegalArgumentException e){
                console.printError("Национальность введёна неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }
    @Override
    public String readLogin() {
        while (true){
            console.write("Введите логин: ");
            String login = console.readLine();
            if (login.isBlank()){
                console.printError("Логин не может быть пустой строкой");
            } else {
                return login;
            }
        }
    }

    @Override
    public String readPassword() {
        while (true){
            console.write("Введите пароль: ");
            String password = console.readLine();
            if (password.isBlank()){
                console.printError("Пароль не может быть пустой строкой");
            } else {
                return password;
            }
        }
    }

}


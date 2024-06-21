package console;

import collectionClasses.Movie;

/**
 * Class {@code BlankConsole} инструмент для чтения разных введенных типов
 * Все методы возвращают либо null, либо ничего
 * @see Movie
 * @see ReaderWriter
 */
public class BlankConsole implements ReaderWriter {
    @Override
    public   Double readDouble(){return null;}
    @Override
    public Integer readInt() {
        return null;
    }

    @Override
    public Float readFloat() {
        return null;
    }

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public void write(String text) {

    }

    @Override
    public void printError(String text) {

    }

    @Override
    public String getValidatedValue(String message) {
        return null;
    }
}

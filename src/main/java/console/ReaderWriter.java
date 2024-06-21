package console;

/**
 * Interface {@code ReaderWriter}
 */
public interface ReaderWriter {
    Integer readInt();
    Double readDouble();
    Float readFloat();
    String readLine();
    void write(String text);
    void printError(String text);
    String getValidatedValue(String message);
}
/**
 * An exception thrown when there is an error reading or writing to a save file.
 */
public class FileErrorDukeException extends DukeException {
    String filePath;

    /**
     * creates the exception.
     *
     * @param filePath The filepath that caused the Error
     */
    public FileErrorDukeException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    /**
     * Gives out a string that describes the exception.
     *
     * @return A string that describes the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry,File at " + filePath + " not Found.";
    }
}

package slave.exception;

/**
 * Exception for when file cannot be read properly
 */
public class UnableToReadFileException extends DukeException {

    /**
     * Constructor for UnableToReadFileException
     * @param index line number where error in reading occurred 
     */
    public UnableToReadFileException(int index){
        super("Unable to read line " + index);
    }
}

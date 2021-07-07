package kappa.exception;

/**
 * Exception for when file cannot be read properly.
 */
public class UnableToReadFileException extends KappaException {

    /**
     * Constructor for UnableToReadFileException.
     *
     * @param index Line number where error in reading occurred.
     */
    public UnableToReadFileException(int index) {
        super("Unable to read line " + index);
    }
}

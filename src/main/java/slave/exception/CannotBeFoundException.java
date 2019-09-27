package slave.exception;

/**
 * Represents an exception where the term cannot be found in the descriptions of any task in the list.
 */
public class CannotBeFoundException extends KappaException {

    /**
     * Constructor for CannotBeFoundException.
     *
     * @param term Term that was searched and cannot be found.
     */
    public CannotBeFoundException(String term) {
        super(String.format("%s cannot be found!", term));
    }
}

package duke.exception;

/**
 * Represents the exception thrown when the tag is not present in the tagList.
 */
public class DukeMissingTagException extends DukeException {

    /**
     * Specifies the message to be printed.
     * @return String which is the message of the exception.
     */
    @Override
    public String toString() {
        return String.format("%s Tag cannot be found!", super.toString());
    }
}

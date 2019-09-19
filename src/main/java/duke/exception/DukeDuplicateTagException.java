package duke.exception;

/**
 * Represents the exception thrown when duplicate tag is present in the tagList.
 */
public class DukeDuplicateTagException extends DukeException {

    /**
     * Specifies the message to be printed.
     * @return String which is the message of the exception.
     */
    @Override
    public String toString() {
        return String.format("%s Task already contains this tag!", super.toString());
    }
}

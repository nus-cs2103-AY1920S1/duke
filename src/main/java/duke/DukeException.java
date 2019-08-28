package duke;

/**
 * Customized DukeException to represent the errors encountered in program.
 *
 */
public class DukeException extends Exception {
    private String description;

    /**
     * Constructor of DukeException.
     *
     * @param description Description of the Error encountered.
     */
    public DukeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

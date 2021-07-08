package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Default duke exception.
     */
    public DukeException() {
        super("-------------------------------------------\n"
                + " OOPS!!!\n I'm sorry, but I don't know what that means\n :-(\n"
                + "-------------------------------------------");
    }

    /**
     * Custom duke exception.
     * @param errorMessage Custom error message
     */
    public DukeException(String errorMessage) {
        super(String.format("-------------------------------------------\n"
                + " OOPS!!!\n %s\n"
                + "-------------------------------------------", errorMessage));
    }
}

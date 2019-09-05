package duke.exceptions;

public class DukeException extends Exception {

    /***
     * Default duke exception.
     */
    public DukeException() {
        super("_____________________________________________________\n" +
                " OOPS!!!\n I'm sorry, but I don't know what that means :-(\n" +
                "_____________________________________________________");
    }

    /***
     * Custom duke exception.
     * @param errorMessage Custom error message
     */
    public DukeException(String errorMessage) {
        super(String.format("_____________________________________________________\n" +
                " OOPS!!!\n %s\n" +
                "_____________________________________________________", errorMessage));
    }
}

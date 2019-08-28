public class DukeException extends Exception {

    /***
     * Default duke exception.
     */
    public DukeException() {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" +
                "    ____________________________________________________________");
    }

    /***
     * Custom duke exception.
     * @param errorMessage Custom error message
     */
    public DukeException(String errorMessage) {
        super(String.format("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! %s \n" +
                "    ____________________________________________________________", errorMessage));
    }
}

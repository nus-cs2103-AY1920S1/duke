public class DukeException extends Exception {
    public DukeException() {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" +
                "    ____________________________________________________________");
    }
    public DukeException(String errorMessage) {
        super(String.format("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! %s \n" +
                "    ____________________________________________________________", errorMessage));
    }
}

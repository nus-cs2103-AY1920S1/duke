public class DukeInvalidCommandException extends DukeExceptions {
    public DukeInvalidCommandException(String displayMsg) {
        super("Invalid user command inputted", displayMsg);
    }
}

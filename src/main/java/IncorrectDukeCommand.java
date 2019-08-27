public class IncorrectDukeCommand extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public IncorrectDukeCommand(String message) {
        super(message);
    }
}
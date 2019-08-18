public class DukeException extends Exception {
    private static final String PREFIX =  "\u2639  OOPS!!!";
    public DukeException(String message) {
        super(String.format("%s %s", PREFIX, message));
    }
}

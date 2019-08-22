public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException(String[] errorMessage) {
        super("The description of a " + errorMessage[0] + " cannot be empty.");
    }

    public DukeException(String[] errorMessage, String date) {
        super("The date of a " + errorMessage[0] + " cannot be empty.");
    }
}
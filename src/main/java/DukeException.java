public class DukeException extends Exception {

    /**
     * Represents the errors which can be thrown in the chatbot.
     * @param message refers to the error message to be displayed to the user
     */
    public DukeException(String message) {
        super(message);
    }

}

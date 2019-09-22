package exceptions;

public class MissingInputException extends DukeException {

    /**
     * Creates MissingInputException when input is not complete for program to process.
     *
     * @param message String to be used for printing error message.
     */
    public MissingInputException(String message) {
        errorMessage += message;
    }

    /**
     * Creates String for error message.
     *
     * @return error message.
     */
    @Override
    public String getErrorMessage() {
        return msg.getLinedMessage(errorMessage);
    }

}

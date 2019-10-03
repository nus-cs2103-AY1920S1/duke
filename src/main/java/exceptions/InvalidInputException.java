package exceptions;

public class InvalidInputException extends DukeException {

    /**
     * Creates InvalidInputException when an invalid input is given.
     * This exception is thrown when
     */
    public InvalidInputException(String message) {
        this.errorMessage += message;
    }

    /**
     * Creates String for error message.
     *
     * @return Error message processed from the {@link ui.MessageGenerator}.
     */
    @Override
    public String getErrorMessage() {
        return msg.getLinedMessage(errorMessage);
    }
}

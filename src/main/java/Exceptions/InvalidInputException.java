package Exceptions;

public class InvalidInputException extends DukeException {

    public InvalidInputException(String message) {
        this.errorMessage += message;
    }

    /**
     * Creates String for error message.
     * @return error message.
     */
    @Override
    public String getErrorMessage() {
        return msg.getLinedMessage(errorMessage);
    }
}

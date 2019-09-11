package Exceptions;

public class InvalidItemException extends DukeException {

    /**
     * Creates InvalidItemException when an invalid list item is given.
     */
    public InvalidItemException() {
        errorMessage += "This item does not exist on the list!";
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

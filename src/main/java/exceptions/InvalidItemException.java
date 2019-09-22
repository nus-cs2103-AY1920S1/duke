package exceptions;

public class InvalidItemException extends DukeException {

    /**
     * Creates InvalidItemException when an invalid list item is given.
     */
    public InvalidItemException() {
        errorMessage += "This item does not exist on the list!";
    }

    /**
     * Creates String for error message.
     *
     * @return      * @return error message processed from the {@link ui.MessageGenerator}.
     */
    @Override
    public String getErrorMessage() {
        return msg.getLinedMessage(errorMessage);
    }

}

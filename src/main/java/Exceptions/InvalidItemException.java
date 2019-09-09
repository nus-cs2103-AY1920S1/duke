package Exceptions;

public class InvalidItemException extends DukeException {

    private String message = "OOPS!!! ";

    /**
     * Creates InvalidItemException when an invalid list item is given.
     */
    public InvalidItemException() {}

    /**
     * Creates String for error message.
     * @return error message.
     */
    @Override
    public String getErrorMessage() {
        message += "This item does not exist on the list";
        return msg.getLinedMessage(message);
    }

}

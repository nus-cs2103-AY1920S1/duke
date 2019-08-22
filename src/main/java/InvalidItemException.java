public class InvalidItemException extends DukeException {

    public InvalidItemException() {
    }

    @Override
    public String errorMessage() {
        message += "This item does not exist on the list";
        return message;
    }

}

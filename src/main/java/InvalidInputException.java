public class InvalidInputException extends DukeException {
    @Override
    public String errorMessage() {
        message += "I'm sorry, but I don't know what your input means :-(";
        return message;
    }
}

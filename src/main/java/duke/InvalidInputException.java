package duke;

public class InvalidInputException extends DukeException {

    private String message = "☹ OOPS!!! ";

    /**
     * Creates String for error message.
     * @return error message.
     */
    @Override
    public String errorMessage() {
        message += "I'm sorry, but I don't know what your input means :-(";
        return message;
    }
}

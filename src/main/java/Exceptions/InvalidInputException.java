package Exceptions;

public class InvalidInputException extends DukeException {

    private String message = "OOPS!!! ";

    /**
     * Creates String for error message.
     * @return error message.
     */
    @Override
    public String getErrorMessage() {
        message += "Date and time should be written in DD/MM/YYYY HH:MM format!";
        return message;
    }
}

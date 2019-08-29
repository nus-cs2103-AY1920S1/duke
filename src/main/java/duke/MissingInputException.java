package duke;

public class MissingInputException extends DukeException {

    private String message = "☹ OOPS!!! ";

    /**
     * Creates MissingInputException when input is not complete for program to process.
     * @param taskType String to be used for printing error message.
     */
    MissingInputException(String taskType) {
        super(taskType);
    }

    /**
     * Creates String for error message.
     * @return error message.
     */
    @Override
    public String errorMessage() {
        message += String.format("The description of a %s cannot be empty.", taskType);
        return message;
    }

}

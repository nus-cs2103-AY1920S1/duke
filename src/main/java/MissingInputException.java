public class MissingInputException extends DukeException {

    public MissingInputException(String taskType) {
        super(taskType);
    }

    @Override
    public String errorMessage() {
        message += String.format("The description of a %s cannot be empty.", taskType);
        return message;
    }

}

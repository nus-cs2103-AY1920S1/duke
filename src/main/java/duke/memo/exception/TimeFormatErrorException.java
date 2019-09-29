package duke.memo.exception;

public class TimeFormatErrorException extends DukeException {
    private String taskType;

    public TimeFormatErrorException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The time format of a " + taskType + " cannot be read.";
    }
}

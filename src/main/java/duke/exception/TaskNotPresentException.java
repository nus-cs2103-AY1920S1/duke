package duke.exception;

public class TaskNotPresentException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s This task is not in list!", super.toString());
    }
}

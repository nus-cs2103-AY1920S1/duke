import exception.DukeUnknownInputException;

public class TaskFactory {
    public Task getTask(TaskType taskType, String[] args) throws DukeUnknownInputException {
        if (taskType == TaskType.TODO) {
            return new Todo(String.join(" ", args));
        }
        if (taskType == TaskType.EVENT) {
            return new Event(args[0].strip(), args[1].strip());
        }
        if (taskType == TaskType.DEADLINE) {
            return new Deadline(args[0].strip(), args[1].strip());
        }
        throw new DukeUnknownInputException("Invalid TaskType given!");
    }
}

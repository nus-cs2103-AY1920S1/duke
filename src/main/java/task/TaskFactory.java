package task;

import exception.DukeUnknownInputException;

public class TaskFactory {
    public Task getTask(TaskType taskType, String[] args) throws DukeUnknownInputException {
        switch (taskType) {
        case TODO:
            return new Todo(String.join(" ", args));
        case EVENT:
            String[] eArgs = String.join(" ", args).split(" /at ");
            return new Event(eArgs[0].strip(), eArgs[1].strip());
        case DEADLINE:
            String[] dArgs = String.join(" ", args).split(" /by ");
            return new Deadline(dArgs[0].strip(), dArgs[1].strip());
        default:
            throw new DukeUnknownInputException("Invalid TaskType enum given!");
        }
    }
}

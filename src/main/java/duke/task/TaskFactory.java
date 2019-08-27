package duke.task;

import duke.exception.DukeUnknownInputException;

/**
 * Implements the Factory pattern. Handles all types of task creation.
 */
public class TaskFactory {
    /**
     * Creates a specific task.
     *
     * @param taskType the type of task to create.
     * @param args the required arguments to initialise the task.
     * @return the created task.
     * @throws DukeUnknownInputException if an unknown TaskType is passed as argument.
     */
    public static Task getTask(TaskType taskType, String[] args) throws DukeUnknownInputException {
        switch (taskType) {
        case TODO:
            // example args: {"borrow", "book"}
            return new Todo(String.join(" ", args));
        case EVENT:
            // example args: {"attend festival", "2pm"}
            String[] eArgs = String.join(" ", args).split(" /at ");
            return new Event(eArgs[0].strip(), eArgs[1].strip());
        case DEADLINE:
            // example args: {"homework", "2/12/2019 1800"}
            String[] dArgs = String.join(" ", args).split(" /by ");
            return new Deadline(dArgs[0].strip(), dArgs[1].strip());
        default:
            throw new DukeUnknownInputException("Invalid TaskType enum given!");
        }
    }

    /**
     * Creates a specific task with specific completion status.
     *
     * @param taskType the type of task to create.
     * @param args the required arguments to initialise the task.
     * @param isDone the completion status of the task.
     * @return the created task.
     * @throws DukeUnknownInputException if an unknown TaskType is passed as argument.
     */
    public static Task getTask(TaskType taskType, String[] args, boolean isDone) throws DukeUnknownInputException {
        switch (taskType) {
        case TODO:
            return new Todo(String.join(" ", args), isDone);
        case EVENT:
            String[] eArgs = String.join(" ", args).split(" /at ");
            return new Event(eArgs[0].strip(), eArgs[1].strip(), isDone);
        case DEADLINE:
            String[] dArgs = String.join(" ", args).split(" /by ");
            return new Deadline(dArgs[0].strip(), dArgs[1].strip(), isDone);
        default:
            throw new DukeUnknownInputException("Invalid TaskType enum given!");
        }
    }
}

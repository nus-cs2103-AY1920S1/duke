package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private String taskType;
    private String taskParams;
    private Task newTask;

    /**
     * Constructs an AddCommand object given task type and task parameters.
     *
     * @throws DukeException if any of the arguments are invalid
     */
    public AddCommand(String taskType, String taskParams) {
        this.taskType = taskType;
        this.taskParams = taskParams;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (taskType) {
        case "todo":
            this.newTask = new Todo(taskParams);
            break;
        case "deadline":
            if (taskParams.isBlank() || taskParams.equals("/by")) {
                throw new DukeException("The description and the due time of a deadline cannot be empty.");
            }
            if (taskParams.startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            if (!taskParams.contains("/by") || taskParams.endsWith("/by")) {
                throw new DukeException("The due time of a deadline cannot be empty.");
            }
            String[] deadlineArgs = taskParams.split("\\s*/by\\s*", 2);
            this.newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            if (taskParams.isBlank() || taskParams.equals("/at")) {
                throw new DukeException("The description and the time of an event cannot be empty.");
            }
            if (taskParams.startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            if (!taskParams.contains("/at") || taskParams.endsWith("/at")) {
                throw new DukeException("The time of an event cannot be empty.");
            }
            String[] eventArgs = taskParams.split("\\s*/at\\s*", 2);
            this.newTask = new Event(eventArgs[0], eventArgs[1]);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(newTask);
        storage.save(tasks);
        ui.showTaskAdditionMsg(newTask, tasks);
    }
}
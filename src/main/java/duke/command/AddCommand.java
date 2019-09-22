package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_ADD;
import static duke.ui.Message.concatLines;
import static duke.ui.Message.getTaskTotalMsg;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Constructs an AddCommand object given task type and task parameters.
     *
     * @throws DukeException if any of the arguments are invalid
     */
    public AddCommand(String taskType, String taskParams) throws DukeException {
        assert taskType != null;
        assert taskParams != null;

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
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        storage.save(tasks);
        tasks.commit();
        return concatLines(MESSAGE_ADD, newTask.getIndentedFormat(), getTaskTotalMsg(tasks));
    }
}
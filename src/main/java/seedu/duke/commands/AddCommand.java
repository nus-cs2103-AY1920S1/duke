package seedu.duke.commands;

import seedu.duke.commons.Messages;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;
import seedu.duke.ui.UI;

/**
 * Evaluates the task type and adds it to the Task list.
 */
public class AddCommand extends Command {
    private Task taskToAdd;
    private String taskType;
    private String partialCommand;

    public AddCommand(String taskType, String partialCommand) {
        this.taskType = taskType;
        this.partialCommand = partialCommand;
    }

    private void evaluateTaskType() throws DukeException {
        String taskDescription;
        String taskDate;
        String[] subparts;
        switch (taskType) {
        case "todo":
            taskDescription = partialCommand;
            taskToAdd = new Todo(taskDescription);
            break;
        case "deadline":
            subparts = partialCommand.split(" /by ");
            taskDescription = subparts[0];
            taskDate = subparts[1];
            taskToAdd = new Deadline(taskDescription, taskDate);
            break;
        case "event":
            subparts = partialCommand.split(" /at ");
            taskDescription = subparts[0];
            taskDate = subparts[1];
            taskToAdd = new Event(taskDescription, taskDate);
            break;
        default:
            throw new DukeException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException, ArrayIndexOutOfBoundsException {
        this.evaluateTaskType();
        tasks.add(taskToAdd);
        String reply = "Got it. I've added this task:\n\t" + taskToAdd + "\nNow you have " + tasks.size()
                + ((tasks.size() == 1) ? " task" : " tasks") + " in the list.";
        return reply;
    }
}

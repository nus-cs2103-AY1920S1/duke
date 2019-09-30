package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

/**
 * This class represents the "delete" command by user and removes the task as specified by the index given by user.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (taskIndex > tasks.size()) {
            throw new DukeException("");
        }
        Task removedTask = tasks.remove(taskIndex - 1);
        String reply = "Noted. I've removed this task:\n\t  " + removedTask + "\n\t" + "Now you have " + tasks.size()
                + ((tasks.size() == 1) ? " task" : " tasks") + " in the list.";
        return reply;
    }
}
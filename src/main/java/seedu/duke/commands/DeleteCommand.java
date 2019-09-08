package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

/** Removes a task from the Task list. If task index is out of the list, throws DukeException.
 * @param taskList Task list for the task to be removed from.
 * @param taskIndex Index of the task to be removed.
 * @param ui Handles the printing of reply.
 * @throws DukeException If task index is more than the size of Task list.
 */
public class DeleteCommand extends Command{
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, UI ui, String fw) throws DukeException {
        if (taskIndex > tasks.size()) {
            throw new DukeException("");
        }
        Task removedTask = tasks.remove(taskIndex - 1);
        String reply = "Noted. I've removed this task:\n\t  " + removedTask + "\n\t" + "Now you have " + tasks.size()
                + ((tasks.size() == 1) ? " task" : " tasks") + " in the list.";
        ui.printReply(reply);
    }
}
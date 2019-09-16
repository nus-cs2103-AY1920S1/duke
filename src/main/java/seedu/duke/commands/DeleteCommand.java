package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.util.List;

public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId - 1;
    }

    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        Task taskToRemove = tasks.remove(taskId);
        Ui.printMessages("Noted. I've removed this task:",
            "  " + taskToRemove.toString(),
            "Now you have " + tasks.size() + " tasks in the list.");
    }
}

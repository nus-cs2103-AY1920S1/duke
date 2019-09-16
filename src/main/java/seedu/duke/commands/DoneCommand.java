package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.util.List;

public class DoneCommand extends Command {

    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId - 1;
    }

    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        tasks.get(taskId).markAsDone();
        Ui.printMessages("Nice! I've marked this task as done:",
            "  " + tasks.get(taskId).toString());
    }
}

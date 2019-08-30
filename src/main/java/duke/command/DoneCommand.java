package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute() throws DukeException {
        Task task = this.taskList.at(taskId);
        task.markAsDone();
        this.ui.displaySingleLine("Nice! I've marked this task as done:");
        this.ui.displayMessage(task.toString(), 2);
        this.storage.saveToDisk(this.taskList);
    }
}

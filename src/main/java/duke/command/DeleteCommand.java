package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes task from TaskList, gives feedback to user after execution.
     *
     * @param tasks task to be deleted
     * @param ui to give feedback to the user
     */
    public String execute(TaskList tasks, Ui ui) {
        Task task = tasks.delete(this.taskNum);
        return ui.showDeleteMessage(task.toString(), tasks.size());
    }
}

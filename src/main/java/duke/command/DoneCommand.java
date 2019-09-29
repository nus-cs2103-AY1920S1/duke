package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Creates a done command.
     * @param taskNum the id of the task
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks task as done, gives feedback to user.
     *
     * @param tasks contains the current list of tasks
     * @param ui to give feedback to the user
     */
    public String execute(TaskList tasks, Ui ui) {
        Task task = tasks.setDone(this.taskNum);
        return ui.showDone(task);
    }
}

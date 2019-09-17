package duke.command;

import duke.Saved;
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
    public void execute(TaskList tasks, Ui ui, Saved file) {
        Task task = tasks.setDone(this.taskNum);
        ui.printDuke("Nice! I've marked this task as done: \n"
                     + "\t" + task + "\n");
    }
}

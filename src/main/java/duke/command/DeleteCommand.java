package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Saved file) {
        Task task = tasks.delete(this.taskNum);
        ui.printDuke("Noted. I've removed this task: \n" +
                     "\t" + task + "\n \tNow you have " + tasks.size() + " tasks in the list.\n");
    }
}

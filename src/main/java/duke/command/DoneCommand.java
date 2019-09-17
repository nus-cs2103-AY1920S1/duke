package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Saved file) {
        Task task = tasks.setDone(this.taskNum);
        ui.printDuke("Nice! I've marked this task as done: \n" +
                     "\t" + task + "\n");
    }
}

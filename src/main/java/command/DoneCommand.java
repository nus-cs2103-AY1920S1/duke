package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDone = tasks.getTasks().get(index - 1);
        taskDone.markAsDone();
        ui.showLine();
        ui.println("     Nice! I've marked this task as done: ");
        ui.println("       " + taskDone.getTypeIcon() + taskDone.getStatusIcon() + " " + taskDone);
        ui.showLine();
        //ui.getUserInput();
    }
}

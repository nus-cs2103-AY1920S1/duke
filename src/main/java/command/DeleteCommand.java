package command;

import command.Command;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskRemoved = tasks.remove(index - 1);
        ui.showLine();
        ui.println("     Noted. I've removed this task: ");
        ui.println("       " + taskRemoved.getTypeIcon() + taskRemoved.getStatusIcon()
                + " " + taskRemoved);
        ui.println("     Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        //ui.getUserInput();
    }
}

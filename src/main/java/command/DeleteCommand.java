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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskRemoved = tasks.remove(index - 1);
        String result = "Noted. I've removed this task: \n"
                +  taskRemoved.getTypeIcon() + taskRemoved.getStatusIcon()
                + " " + taskRemoved + "\n" + "Now you have " + tasks.size() + " tasks in the list.";

        return result;
    }
}

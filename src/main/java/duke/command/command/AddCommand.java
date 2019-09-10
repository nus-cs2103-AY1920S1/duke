package duke.command.command;

import duke.command.Command;
import duke.task.Task;
import error.command.CommandCreationException;
import error.ui.UiException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) throws CommandCreationException {
        super(null);
        this.task = task;
    }

    @Override
    public void execute() throws UiException {
        tasksController.addTask(task);
    }
}

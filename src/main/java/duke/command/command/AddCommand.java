package duke.command.command;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.Task;
import error.command.CommandCreationException;
import error.ui.UiException;

import java.util.Optional;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) throws CommandCreationException {
        super(null);
        this.task = task;
    }

    @Override
    public void execute() throws UiException {
        tasksController.addTask(task, true);
    }

    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.of(() -> tasksController.deleteTaskByUuid(task.getUuid(), false));
    }
}

package duke.command.done;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.command.CommandNotExecutedException;
import error.ui.UiException;

import java.util.Optional;

/**
 * Command to mark tasks as done.
 */
public class DoneCommand implements Command {
    private final int index;
    private TasksController tasksController;

    private boolean isExecuted;
    private boolean isSuccessful;

    public DoneCommand(int index, TasksController tasksController) throws CommandCreationException {
        this.tasksController = tasksController;
        this.index = index;
    }

    /**
     * Sets task to done.
     */
    @Override
    public void execute() throws UiException {
        this.isSuccessful = tasksController.setTaskToDone(index);
        this.isExecuted = true;
    }

    /**
     * Returns UndoAction to unmark task as done.
     * @return empty if task was not successfully marked
     */
    @Override
    public Optional<UndoAction> getUndoAction() throws CommandNotExecutedException {
        if (!this.isExecuted) {
            throw new CommandNotExecutedException();
        }

        if (this.isSuccessful) {
            return Optional.of(() -> tasksController.setTaskToUndone(index));
        }

        return Optional.empty();
    }
}

package duke.command.command;

import duke.command.Command;
import duke.command.entities.UndoAction;
import duke.task.Task;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

/**
 * Command to add a new task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     * @param task task to be added
     * @throws CommandCreationException if arguments are invalid
     */
    public AddCommand(Task task, UiController ui, TasksController tasksController) throws CommandCreationException {
        super(null, ui, tasksController);
        this.task = task;
    }

    /**
     * Adds task.
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        tasksController.addTask(task, true);
    }

    /**
     * Returns UndoAction to remove the added task.
     * @return Optional of UndoAction that is always present
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.of(() -> tasksController.deleteTaskByUuid(task.getUuid(), true));
    }
}

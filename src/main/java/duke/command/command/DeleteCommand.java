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
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int deletedTaskIndex;
    private Optional<Task> deleted;

    private static final String INVALID_INDEX_MESSAGE = "☹ OOPS!!! PLease enter a valid index! :-(";

    /**
     * Constructor for DeleteCommand.
     * @param argument index of task to be deleted
     * @throws CommandCreationException if arguments are invalid
     */
    public DeleteCommand(String argument, UiController ui, TasksController tasksController) throws CommandCreationException {
        super(null, ui, tasksController);

        try {
            deletedTaskIndex = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new CommandCreationException(INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Attempts to delete the task.
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        this.deleted = tasksController.deleteTask(deletedTaskIndex);
    }

    /**
     * Returns UndoAction to add back the task if task was successfully deleted.
     * @return empty if task was not deleted successfully
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        if (deleted.isEmpty()) {
            return Optional.empty();
        }

        Task restore = deleted.get();
        return Optional.of(() -> tasksController.addTask(restore, true));
    }
}

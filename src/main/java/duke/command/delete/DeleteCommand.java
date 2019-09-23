package duke.command.delete;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.Task;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.command.CommandNotExecutedException;
import error.ui.UiException;

import java.util.List;
import java.util.Optional;

/**
 * Command to delete a task.
 */
public class DeleteCommand implements Command {
    private int deletedTaskIndex;
    private TasksController tasksController;
    private boolean deleteAll;

    private Task deletedTask;
    private List<Task> deletedTasks;
    private boolean isExecuted;

    DeleteCommand(int index, boolean deleteAll, TasksController tasksController) throws CommandCreationException {
        this.deletedTaskIndex = index;
        this.tasksController =tasksController;
        this.deleteAll = deleteAll;

        this.isExecuted = false;
    }

    /**
     * Attempts to delete the task.
     *
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        if (this.deleteAll) {
            this.deletedTasks = tasksController.deleteAllTasks();
            this.isExecuted = true;

            return;
        }

        this.deletedTask = tasksController.deleteTask(deletedTaskIndex);
        this.isExecuted = true;
    }

    /**
     * Returns UndoAction to add back the task if task was successfully deleted.
     *
     * @return empty if task was not deleted successfully
     */
    @Override
    public Optional<UndoAction> getUndoAction() throws CommandNotExecutedException {
        if (!this.isExecuted) {
            throw new CommandNotExecutedException();
        }

        if (this.deleteAll && this.deletedTasks != null) {
            return Optional.of(() -> tasksController.setNewTasks(deletedTasks));
        }

        if (!this.deleteAll && this.deletedTask != null) {
            return Optional.of(() -> tasksController.addTaskToIndex(deletedTaskIndex, deletedTask));
        }

        return Optional.empty();
    }
}

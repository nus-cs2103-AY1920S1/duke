package duke.command.command;

import duke.command.Command;
import duke.command.entities.UndoAction;
import duke.task.Task;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.storage.StorageException;
import error.ui.UiException;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int deletedTaskIndex;
    private Optional<Task> deleted;
    private List<Task> prevList;
    private boolean deleteAll;

    private static final String INVALID_INDEX_MESSAGE = "☹ OOPS!!! PLease enter a valid index! :-(";
    private static final String STORAGE_ERROR_MESSAGE = "☹ OOPS!!! Unable to access storage file! :-(";

    /**
     * Constructor for DeleteCommand.
     *
     * @param argument index of task to be deleted
     * @throws CommandCreationException if arguments are invalid
     */
    public DeleteCommand(String argument, Ui ui, TasksController tasksController) throws CommandCreationException {
        super(null, ui, tasksController);

        // first store previous list for it to be restored if needed
        try {
            this.prevList = new ArrayList<>();
            prevList.addAll(tasksController.listTasks());
        } catch (StorageException e) {
            throw new CommandCreationException(STORAGE_ERROR_MESSAGE);
        }

        // set flag to delete all in execute method and return
        if (argument.equals("all")) {
            deleteAll = true;
            return;
        }

        // check if argument is a valid number
        try {
            deletedTaskIndex = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new CommandCreationException(INVALID_INDEX_MESSAGE);
        }

        // set flag to only delete one
        deleteAll = false;
    }



    /**
     * Attempts to delete the task.
     *
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        if (this.deleteAll) {
            tasksController.deleteAllTasks();
            return;
        }

        this.deleted = tasksController.deleteTask(deletedTaskIndex);
    }

    /**
     * Returns UndoAction to add back the task if task was successfully deleted.
     *
     * @return empty if task was not deleted successfully
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        if (this.deleteAll) {
            return Optional.of(() -> {
                ui.displayOutput("Noted. I have reverted your list of tasks.");
                tasksController.setNewTasksList(prevList, false);
            });
        }

        if (deleted.isEmpty()) {
            return Optional.empty();
        }

        Task tobeRestored = deleted.get();
        return Optional.of(() -> tasksController.addTask(tobeRestored, true));
    }
}

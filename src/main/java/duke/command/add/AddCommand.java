package duke.command.add;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.Task;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.command.CommandNotExecutedException;
import error.ui.UiException;


import java.util.Optional;

/**
 * Command to add a new task.
 */
public class AddCommand implements Command {
    private Task task;
    private TasksController tasksController;
    private boolean isExecuted;
    private boolean isSuccessful;

    /**
     * Constructor for add command.
     * @param task task to be added.
     * @param tasksController the controller that will be responsible for adding the task.
     */
    public AddCommand(Task task, TasksController tasksController) {
        this.task = task;
        this.tasksController = tasksController;
        this.isExecuted = false;
    }

    /**
     * Adds task.
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        this.isSuccessful = tasksController.addTask(task);
        this.isExecuted = true;
    }

    /**
     * Returns UndoAction to remove the added task.
     * @return Optional of UndoAction that is always present
     */
    @Override
    public Optional<UndoAction> getUndoAction() throws CommandNotExecutedException {
        if (!isExecuted) {
            throw new CommandNotExecutedException();
        }

        if (this.isSuccessful) {
            return Optional.of(() -> tasksController.deleteTaskByUuid(task.getUuid()) != null);
        } else {
            return Optional.empty();
        }
    }
}

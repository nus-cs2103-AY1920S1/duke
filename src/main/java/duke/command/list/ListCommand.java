package duke.command.list;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;

import java.util.Optional;

/**
 * Command to list all tasks in memory.
 */
public class ListCommand implements Command {
    TasksController tasksController;

    public ListCommand(TasksController tasksController) throws CommandCreationException {
        this.tasksController = tasksController;
    }

    /**
     * Display tasks.
     */
    @Override
    public void execute() throws UiException {
        tasksController.listTasks();
    }

    /**
     * Always returns empty.
     *
     * @return empty optional
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}

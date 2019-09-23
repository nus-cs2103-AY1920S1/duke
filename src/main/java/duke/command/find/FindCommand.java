package duke.command.find;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.Ui;

import java.util.Optional;

/**
 * Command to find tasks.
 */
public class FindCommand implements Command {
    private String parameter;
    private TasksController tasksController;

    FindCommand(String parameter, TasksController tasksController) {
        this.parameter = parameter;
        this.tasksController = tasksController;
    }

    /**
     * Search for task.
     */
    @Override
    public void execute() throws UiException {
        this.tasksController.findTasks(this.parameter);
    }

    /**
     * Always returns empty.
     * @return empty optional
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}

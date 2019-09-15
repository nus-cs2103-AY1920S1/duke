package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

/**
 * Command to find tasks.
 */
public class FindCommand extends Command {
    private String searchParameter;

    public FindCommand(String arguments, UiController ui, TasksController tasksController) throws CommandCreationException {
        super(CommandType.FIND, ui, tasksController);
        searchParameter = arguments;
    }

    /**
     * Search for task.
     */
    @Override
    public void execute() throws UiException {
        tasksController.findTasks(searchParameter);
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

package duke.command.command;

import duke.command.Command;
import duke.command.entities.CommandType;
import duke.command.entities.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

/**
 * Command to find tasks.
 */
public class FindCommand extends Command {
    private static final String EMPTY_ARGUMENT_MESSAGE = "☹ OOPS!!! Please enter a search parameter! :-(";

    private String searchParameter;

    public FindCommand(String arguments, UiController ui, TasksController tasksController) throws CommandCreationException {
        super(CommandType.FIND, ui, tasksController);

        if (arguments.equals("")) {
            throw new CommandCreationException(EMPTY_ARGUMENT_MESSAGE);
        }
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

package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import error.command.CommandCreationException;
import error.ui.UiException;

import java.util.Optional;

/**
 * Command to find tasks.
 */
public class FindCommand extends Command {
    private String searchParameter;

    public FindCommand(String arguments) throws CommandCreationException {
        super(CommandType.FIND);
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

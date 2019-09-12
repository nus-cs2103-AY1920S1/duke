package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import error.command.CommandCreationException;
import error.ui.UiException;

import java.util.Optional;

/***
 * <p>
 * Command to list all tasks in memory.
 * </p>
 */
public class ListCommand extends Command {
    private static final String INVALID_ARGUMENT_MESSAGE = "☹ OOPS!!! List command doesn't accept arguments! :-(";

    public ListCommand(String s) throws CommandCreationException {
        super(CommandType.LIST);
        if (!s.equals("")) {
            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
        }
    }

    /***
     * <p>
     * Display tasks.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() throws UiException {
        tasksController.displayAllTasks();
    }

    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}

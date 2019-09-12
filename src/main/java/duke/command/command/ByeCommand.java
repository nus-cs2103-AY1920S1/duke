package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import error.command.CommandCreationException;
import error.ui.UiException;

import java.util.Optional;

/***
 * <p>
 * Command to exit the application.
 * </p>
 */
public class ByeCommand extends Command {
    private static final String INVALID_ARGUMENT_MESSAGE = "☹ OOPS!!! Bye command doesn't accept arguments! :-(";

    public ByeCommand(String s) throws CommandCreationException {
        super(CommandType.BYE);
        if (!s.equals("")) {
            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
        }
    }

    @Override
    public void execute() throws UiException {
        ui.exit();
    }

    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}

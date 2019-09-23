package duke.command.bye;

import duke.command.Command;
import duke.command.UndoAction;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.Ui;

import java.util.Optional;

/**
 * Command to exit the application.
 */
public class ByeCommand implements Command {
    private Ui ui;

    public ByeCommand(Ui ui) throws CommandCreationException {
        this.ui = ui;
    }

    /**
     * Closes ui interface.
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        this.ui.stopUi();
    }

    /**
     * Always returns empty optional.
     * @return empty optional
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}

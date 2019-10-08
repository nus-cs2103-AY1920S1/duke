package duke.command.help;

import duke.command.Command;
import duke.command.UndoAction;
import error.command.CommandNotExecutedException;
import error.ui.UiException;
import ui.Ui;

import java.util.Optional;

public class HelpCommand implements Command {
    Ui ui;

    HelpCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() throws UiException {
        ui.displayOutput(HelpDoc.HELP_DOC);
    }

    /**
     * Always returns an empty optional.
     * @return an empty Optional.
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}

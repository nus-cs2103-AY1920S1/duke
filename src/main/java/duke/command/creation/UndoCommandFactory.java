package duke.command.creation;

import duke.CommandExecutor;
import duke.command.Command;
import duke.command.UndoAction;
import error.command.CommandCreationException;
import error.command.CommandNotExecutedException;
import error.ui.UiException;
import ui.Ui;
import util.strings.CommandSplitter;

import java.util.Optional;

public class UndoCommandFactory implements CommandFactory {
    private final CommandExecutor commandExecutor;
    private Ui ui;

    public UndoCommandFactory(CommandExecutor commandExecutor, Ui ui) {
        this.commandExecutor = commandExecutor;
        this.ui = ui;
    }

    @Override
    public Optional<Command> getCommandFromUserInput(String input) throws CommandCreationException {
        if (!CommandSplitter.getCommand(input).equals("undo")) {
            return Optional.empty();
        }

        if (!CommandSplitter.getArguments(input).equals("")) {
            throw new CommandCreationException("Arguments, this command does not accept.");
        }

        UndoAction undoAction = this.commandExecutor.getLatestUndoAction();
        if (undoAction == null) {
            throw new CommandCreationException("No actions to undo you have, I'm afraid.");
        }

        Command undoCommand = new Command() {

            @Override
            public void execute() throws UiException {
                UndoCommandFactory.this.ui.displayOutput("Noted. Undone your latest action I have.");
                undoAction.undo();
            }

            @Override
            public Optional<UndoAction> getUndoAction() throws CommandNotExecutedException {
                return Optional.empty();
            }
        };

        return Optional.of(undoCommand);

    }
}

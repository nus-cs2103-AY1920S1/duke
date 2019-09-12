package duke.command;

import error.ui.UiException;

import java.util.ArrayList;
import java.util.Stack;

public class CommandExecutor {
    private Stack<UndoAction> undoActions;

    public CommandExecutor() {
        this.undoActions = new Stack<>();
    }

    public void executeCommand(Command command) throws UiException {
        command.execute();
        command.getUndoAction().ifPresent(undoActions::push);
    }

    public void undoPreviousCommand() throws UiException {
        this.undoActions.pop().undo();
    }
}

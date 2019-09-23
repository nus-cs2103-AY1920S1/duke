package duke;

import duke.command.Command;
import duke.command.UndoAction;
import error.ui.UiException;

import java.util.Stack;

/**
 * Class used by the program to execute commands. It keeps a history of UndoActions to revert any changes made
 * by the user.
 */
public class CommandExecutor {
    private Stack<UndoAction> undoActions;

    public CommandExecutor() {
        this.undoActions = new Stack<>();
    }

    /**
     * Executes the command and adds its correpsonding UndoAction to memory if it exists.
     * @param command the command to be executed by the program.
     * @throws UiException if the ui fails unexpectedly.
     */
    public void executeCommand(Command command) throws UiException {
        command.execute();
        command.getUndoAction().ifPresent(undoActions::push);
    }

    /**
     * Returns the latest UndoAction to revert the latest changes made by the user.
     * @return the latest UndoAction.
     */
    public UndoAction getLatestUndoAction() {
        if (this.undoActions.isEmpty()) {
            return null;
        }

        return this.undoActions.pop();
    }
}

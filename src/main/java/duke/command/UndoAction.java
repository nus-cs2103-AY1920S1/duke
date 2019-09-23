package duke.command;

import error.ui.UiException;

/**
 * Undoes a command's action.
 */
@FunctionalInterface
public interface UndoAction {
    public boolean undo() throws UiException;
}

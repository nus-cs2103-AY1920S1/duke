package duke.command.entities;

import error.ui.UiException;

/**
 * Undoes a command's action.
 */
@FunctionalInterface
public interface UndoAction {
    public void undo() throws UiException;
}

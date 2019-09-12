package duke.command;

import error.ui.UiException;

@FunctionalInterface
public interface UndoAction {
    public void undo() throws UiException;
}

package ui;

import error.ui.UiException;

/**
 * Interface to expose the DukeOutput output channel of a UiController to external clients without exposing the
 * implementation of the UiController. If a client wishes to display something to an existing ui, it would obtain
 * a UiOutputAccessor from the corresponding UiController through its getUiOutputAccessor() method and use this
 * accessor to display output.
 */
public interface UiOutputAccessor {
    public void displayOutput(String output) throws UiException;
}

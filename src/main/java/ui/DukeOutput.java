package ui;

import error.ui.UiException;

/**
 * An abstract class to encapsulate an output channel for the application's Ui. It provides a method for the application
 * to display any messages to the corresponding output channel.
 */
public abstract class DukeOutput {
    /**
     * Displays output message in a corresponding output channel. Any calls to displayOutput(String message) before
     * start() is called all after stop() is called SHOULD THROW a UiException.
     * @param message output to be displayed in the output channel
     * @throws UiException when implemented output channel fails to display the output.
     */
    public abstract void displayOutput(String message) throws UiException;

    /**
     * Opens the output channel.
     */
    public abstract void startOutputChannel();

    /**
     * Closes the output channel.
     */
    public abstract void stopOutputChannel();
}

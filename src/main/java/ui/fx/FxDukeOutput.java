package ui.fx;

import error.ui.UiException;
import ui.DukeOutput;

/**
 * Class to encapsulate a JavaFX application as a DukeOutput for the application.
 */
public class FxDukeOutput extends DukeOutput {
    private boolean isOpen;

    /**
     * Displays output message in a corresponding output channel. Any calls to displayOutput(String message) before
     * start() is called all after stop() is called SHOULD THROW a UiException.
     *
     * @param message output to be displayed in the output channel
     */
    @Override
    public void displayOutput(String message) {
        if (this.isOpen && FxMain.getActiveMainWindowController() != null) {
            FxMain.getActiveMainWindowController().printDukeMessage(message);
        }
    }

    /**
     * Opens the output channel.
     */
    @Override
    public void startOutputChannel() {
        this.isOpen = true;
    }

    /**
     * Closes the output channel.
     */
    @Override
    public void stopOutputChannel() {
        this.isOpen = false;
    }
}

package ui;

import error.ui.UiException;

import java.util.ArrayList;
import java.util.List;

public class StubOutput extends DukeOutput {
    List<String> receivedOutputs = new ArrayList<>();
    boolean isOpen;

    /**
     * Displays output message in a corresponding output channel. Any calls to displayOutput(String message) before
     * start() is called all after stop() is called SHOULD THROW a UiException.
     *
     * @param message output to be displayed in the output channel
     * @throws UiException when implemented output channel fails to display the output.
     */
    @Override
    public void displayOutput(String message) throws UiException {
        receivedOutputs.add(message);
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

    public List<String> getReceivedOutputs() {
        return this.receivedOutputs;
    }
}

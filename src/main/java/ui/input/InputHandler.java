package ui.input;

import ui.output.OutputHandler;

/**
 * Interface for an input channel.
 */
public interface InputHandler {
    /**
     * Notifies listeners of any user input.
     * @param input user input
     */
    public void updateAllListeners(String input);

    /**
     * Adds a new listener to the input channel.
     * @param listener input listener
     */
    public void addListener(InputListener listener);

    /**
     * Starts listening to input.
     * @param output corresponding output channel
     */
    public void startHandler(OutputHandler output);

    /**
     * Closes the channel.
     */
    public void stopHandler();
}

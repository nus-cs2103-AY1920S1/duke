package ui;

/**
 * Interface to encapsulate a listener for DukeInput's input channel. The listener is responsible for
 * parsing and responding to the input.
 */
public interface DukeInputListener {
    /**
     * Method with which DukeInput notifies the listener of any user input.
     * @param input user input from input channel
     */
    void receiveInput(String input);
}

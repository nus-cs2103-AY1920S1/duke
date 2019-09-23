package ui;

import error.ui.UiInitializationException;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to encapsulate a user input channel. It uses the observer pattern to notify all its listeners
 * of any user input that it receives. Any input received after the method start() is called SHOULD BE forwarded to all
 * its listeners using the updateAllListeners(String input) method.
 */
public abstract class DukeInput {
    private List<DukeInputListener> listeners;
    private boolean isBlocking;

    protected DukeInput(boolean isBlocking) {
        this.listeners = new ArrayList<>();
        this.isBlocking = isBlocking;
    }

    protected void updateAllListeners(String input) {
        this.listeners.forEach(listener -> listener.receiveInput(input));
    }

    /**
     * Returns true if this input channel is blocking.
     * @return information about whether the input channel is blocking.
     */
    public boolean isBlocking() {
        return this.isBlocking;
    }

    /**
     * Adds a listener to the input channel. This listener will be notified of any user input received by the input
     * channel through its receiveInput(String input) method.
     * @param listener the listener to be added.
     */
    public void addListener(DukeInputListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Starts listening to user input. Any input received SHOULD BE forwarded to its listeners by passing the input
     * into the updateAllListeners(String input) method. In the event that the input channel is blocking, it SHOULD
     * start the dukeOutput channels first.
     * @param dukeOutputs output channels to be opened before the input channel.
     */
    protected abstract void startInputChannel(DukeOutput... dukeOutputs) throws UiInitializationException;

    /**
     * Stops listening to user input. Any input received afterwards SHOULD NOT BE forwarded to the controller anymore.
     */
    protected abstract void stopInputChannel();
}

package ui;

import java.util.List;

public abstract class InputHandler {
    private List<InputListener> listeners;

    protected void addListener(InputListener listener) {
        listeners.add(listener);
    }

    protected void updateAllListeners(String input) {
        listeners.forEach(listener -> listener.update(input));
    }

    protected abstract void start();
}

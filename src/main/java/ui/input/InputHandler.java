package ui.input;

import java.util.List;

public abstract class InputHandler {
    private List<InputListener> listeners;

    protected void updateAllListeners(String input) {
        listeners.forEach(listener -> listener.update(input));
    }

    public void addListener(InputListener listener) {
        listeners.add(listener);
    }

    public abstract void start();
    public abstract void stop();
}

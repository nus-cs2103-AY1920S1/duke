package ui.input;

import ui.output.OutputHandler;

public interface InputHandler {
    public void updateAllListeners(String input);
    public void addListener(InputListener listener);
    public void startHandler(OutputHandler output);
    public void stopHandler();
}

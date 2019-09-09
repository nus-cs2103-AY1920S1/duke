package ui;

import error.ui.UiException;
import ui.input.InputHandler;
import ui.input.InputListener;
import ui.output.OutputHandler;

public class UiController implements InputListener {
    private InputHandler input;
    private OutputHandler output;

    private boolean isStarted;

    private UiActivity activity;

    public UiController(InputHandler input,OutputHandler output, UiActivity activity) {
        this.input = input;
        this.output = output;
        this.activity = activity;

        this.isStarted = false;
    }

    public void initializeUi() {
        // start listening to inputs
        isStarted = true;
        input.addListener(this);
        input.startHandler(output);
    }

    public void displayOutput(String message) throws UiException {
        assert isStarted;
        output.display(message); // output only allowed to print after it is initialized with the input
    }

    @Override
    public void update(String input) {
        // sends input back to activity utilizing the ui
        activity.onInputReceived(input);
    }

    public void exit() {
        output.display("Bye! Hope to see you again soon.");
        input.stopHandler();
        activity.stopActivity();
    }
}

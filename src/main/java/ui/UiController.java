package ui;

import error.ui.UiException;
import ui.input.InputHandler;
import ui.input.InputListener;
import ui.output.OutputHandler;

/**
 * Controller to mediate user input and output as a single UI entity.
 */
public class UiController implements InputListener {
    private InputHandler input;
    private OutputHandler output;

    private boolean isStarted;

    private UiActivity activity;

    /**
     * Constructor for UIController.
     * @param input input channel
     * @param output output channel
     * @param activity ui driver
     */
    public UiController(InputHandler input,OutputHandler output, UiActivity activity) {
        this.input = input;
        this.output = output;
        this.activity = activity;

        this.isStarted = false;
    }

    /**
     * Starts listening for inputs.
     */
    public void initializeUi() {
        // start listening to inputs
        isStarted = true;
        input.addListener(this);
        input.startHandler(output);
    }

    /**
     * Prints output.
     * @param message message to be printed
     * @throws UiException if ui fails unexpectedly
     */
    public void displayOutput(String message) throws UiException {
        assert isStarted;
        output.display(message); // output only allowed to print after it is initialized with the input
    }

    /**
     * Response to input.
     * @param input input from input channel
     */
    @Override
    public void update(String input) {
        // sends input back to activity utilizing the ui
        activity.onInputReceived(input);
    }

    /**
     * Closes ui.
     */
    public void exit() {
        output.display("Bye! Hope to see you again soon.");
        input.stopHandler();
        activity.stopActivity();
    }
}

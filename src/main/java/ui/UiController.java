package ui;

public class UiController implements InputListener {
    private InputHandler input;
    private OutputHandler output;

    private UiActivity activity;

    public UiController(InputHandler input,OutputHandler output, UiActivity activity) {
        this.input = input;
        this.output = output;
        this.activity = activity;
    }

    public void start() {
        // start listening to inputs
        input.addListener(this);
        input.start();
    }

    public void displayOutput(String message) {
        output.display(message);
    }

    @Override
    public void update(String input) {
        // sends input back to activity utilizing the ui
        activity.onInputReceived(input);
    }
}

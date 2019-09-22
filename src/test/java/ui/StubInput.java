package ui;

public class StubInput extends DukeInput {
    String input;
    boolean isOpen;

    public StubInput(String input) {
        super(false);
        this.input =input;
    }

    /**
     * Starts listening to user input. Any input received SHOULD BE forwarded to its listeners by passing the input
     * into the updateAllListeners(String input) method. In the event that the input channel is blocking, it SHOULD
     * start the dukeOutput channels first.
     *
     * @param dukeOutputs output channels to be opened before the input channel.
     */
    @Override
    protected void startInputChannel(DukeOutput... dukeOutputs) {
        this.isOpen = true;
        if (input != null) {
            updateAllListeners(input);
        }
    }

    /**
     * Stops listening to user input. Any input received afterwards SHOULD NOT BE forwarded to the controller anymore.
     */
    @Override
    protected void stopInputChannel() {
        this.isOpen = false;
    }
}

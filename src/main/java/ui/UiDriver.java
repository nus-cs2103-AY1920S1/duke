package ui;

/**
 * An interface to encapsulate a host of Duke's Ui interfaces. It will be in charge of parsing and responding to
 * user inputs received by the Ui and also sending display messages back to the Ui.
 */
public interface UiDriver {
    /**
     * Method used by UiController to notify the driver of any user input.
     * @param input input from the UiController.
     */
    public void receiveUserInput(String input);

    /**
     * Method used by UiController to notify the driver once it has closed. The driver should respond accordingly.
     */
    public void onUiClosed();
}

package ui;

/**
 * An interface to encapsulate a host of Duke's Ui interfaces. It will be in charge of parsing and responding to
 * user inputs received by the Ui and also sending display messages back to the Ui.
 */
public interface UiDriver {
    public void receiveUserInput(String input);
    public void stopActivity();
}

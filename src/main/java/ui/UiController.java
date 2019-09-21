package ui;

import error.ui.UiException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class to encapsulate a controller for the UI component of the Duke chatbox. It acts as a mediator between
 * the direct Ui components and the main program driver. It contains 2 inner components, DukeInput.java and DukeOutput. These
 * two components serve as direct handlers for getting user input and printing output messages respectively. DukeUi
 * helps to orchestrate the Ui's behavior by forwarding any input to the main program driver and by providing a method
 * for the main program driver to display any messages.
 */
public class UiController implements DukeInputListener {
    private DukeInput inputChannel;
    private DukeOutput outputChannel;

    private UiDriver driver;

    private boolean isUiInitialized;

    protected UiController(DukeInput dukeInput, DukeOutput dukeOutput, UiDriver driver) {
        this.inputChannel = dukeInput;
        this.inputChannel.addListener(this);

        this.outputChannel = dukeOutput;
        this.driver = driver;

        this.isUiInitialized = false;
    }

    /**
     * Method used by DukeInput to notify the controller of any user input received. Input is then forwarded to the
     * main program driver through its receiveUserInput(String input) method for handling. This method SHOULD NOT be
     * used by any other classes.
     * @param input the user input received by DukeInput's input channel.
     */
    @Override
    public void receiveInput(String input) {
        this.driver.receiveUserInput(input);
    }

    /**
     * Displays output through internal DukeOutput component. Output CAN ONLY be displayed after initializeUi() has been
     * called to open the output channel.
     * @param output output message to be displayed.
     * @throws UiException if message fails to be displayed by the output channel or if ui has not been previously
     * initialized.
     */
    public void displayOutput(String output) throws UiException {
        if (!isUiInitialized) {
            throw new UiException("Ui has not been initialized.");
        }

        this.outputChannel.displayOutput(output);
    }

    /**
     * Initializes Ui by opening the corresponding input and output channels of DukeInput and DukeOutput instances. Ui
     * must be initialized before controller can start receiving inputs and it can display any output.
     */
    public void initializeUi() {
        this.isUiInitialized = true;

        this.inputChannel.startInputChannel(this.outputChannel);
    }

    /**
     * Stops the ui properly by closing the corresponding input and output channels of the DukeInput and DukeOutput
     * instances.
     */
    public void stopUi() {
        try {
            outputChannel.displayOutput("Bye! Hope to see you again!");
        } catch (UiException e) {
            System.out.println("Ui closed unexpectedly.");
        } finally {
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
                this.inputChannel.stopInputChannel();
                this.outputChannel.stopOutputChannel();
            });
        }
    }
}

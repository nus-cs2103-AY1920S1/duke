package ui.fx;

import error.ui.UiInitializationException;
import javafx.application.Application;
import javafx.application.Platform;
import ui.DukeInput;
import ui.DukeOutput;

import java.util.Arrays;

/**
 * Class to encapsulate a JavaFX application as a DukeInput for the application.
 */
public class FxDukeInput extends DukeInput {
    public FxDukeInput() {
        super(true);
    }

    /**
     * Starts listening to user input. Any input received SHOULD BE forwarded to its listeners by passing the input
     * into the updateAllListeners(String input) method. In the event that the input channel is blocking, it SHOULD
     * start the dukeOutput channels first.
     *
     * @param dukeOutputs output channels to be opened before the input channel.
     */
    @Override
    protected void startInputChannel(DukeOutput... dukeOutputs) throws UiInitializationException {
        for (DukeOutput outputChannel: dukeOutputs) {
            outputChannel.startOutputChannel();
        }

        FxMain.addListeners(this);

        if (!FxMain.isIsApplicationStarted()) {
            Application.launch(FxMain.class);
        }
    }

    /**
     * Stops listening to user input. Any input received afterwards SHOULD NOT BE forwarded to the controller anymore.
     */
    @Override
    protected void stopInputChannel() {
        Platform.exit();
    }

    /**
     * Method used by the MainWindowController instance to notify FxDukeInput of received user inputs. FxDukeInput then
     * forwards the input to its listeners.
     * @param input input received from mainWindowController.
     */
    void receiveInput(String input) {
        updateAllListeners(input);
    }
}

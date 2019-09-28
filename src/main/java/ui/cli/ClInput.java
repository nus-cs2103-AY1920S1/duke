package ui.cli;


import error.ui.UiInitializationException;
import ui.DukeInput;
import ui.DukeOutput;

import java.util.Scanner;

/**
 * Encapsulates the command line as a DukeInput input channel for the application.
 */
public class ClInput extends DukeInput {
    private static String STOP_SCANNER_INDICATOR = "f3ezlsm3nlrzc4v4fymu";
    private static Scanner scanner;
    private static boolean isActive;

    public ClInput() {
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

        if (ClInput.scanner == null) {
            scanner = new Scanner(System.in);
        }

        if (!ClInput.isActive) {
            isActive = true;
        }

        while (ClInput.isActive) {
            String input = scanner.nextLine();

            if (input.equals(STOP_SCANNER_INDICATOR)) {
                break;
            }

            updateAllListeners(input);
        }
    }

    /**
     * Stops listening to user input. Any input received afterwards SHOULD NOT BE forwarded to the controller anymore.
     */
    @Override
    protected void stopInputChannel() {
        ClInput.isActive = false;
    }
}
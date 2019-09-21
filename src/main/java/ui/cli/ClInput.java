package ui.cli;


import ui.DukeInput;
import ui.DukeOutput;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Encapsulates the command line as a DukeInput input channel for the application.
 */
public class ClInput extends DukeInput {
    private static Scanner scanner;
    private static boolean isActive;

    private static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING =
            DUKE_LOGO + "\n" + "\n"
                    + "Hello! I'm duke.Duke\n"
                    + "What can I do for you?";

    /**
     * Starts listening to user input. Any input received SHOULD BE forwarded to its listeners by passing the input
     * into the updateAllListeners(String input) method. In the event that the input channel is blocking, it SHOULD
     * start the dukeOutput channels first.
     *
     * @param dukeOutputs output channels to be opened before the input channel.
     */
    @Override
    protected void startInputChannel(DukeOutput... dukeOutputs) {
        Arrays.stream(dukeOutputs).forEach(DukeOutput::startOutputChannel);

        if (ClInput.scanner == null) {
            scanner = new Scanner(System.in);
        }

        if (!ClInput.isActive) {
            isActive = true;
        }

        while (ClInput.isActive) {
            String input = scanner.nextLine();
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
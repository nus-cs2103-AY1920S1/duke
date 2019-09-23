package ui.cli;

import error.ui.UiException;
import error.ui.UiInitializationException;
import ui.DukeOutput;

/**
 * Encapsulates the command line as a DukeOutput output channel for the application.
 */
public class ClOutput extends DukeOutput {

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

    private boolean isOpen;

    /**
     * Displays output message in a corresponding output channel. Any calls to displayOutput(String message) before
     * start() is called all after stop() is called SHOULD THROW a UiException.
     * @param message output to be displayed in the output channel
     * @throws UiException when implemented output channel fails to display the output.
     */
    @Override
    public void displayOutput(String message) throws UiException {
        if (isOpen) {
            StringBuilder builder = new StringBuilder();

            String messageWithIndent = message.replaceAll("(?m)^", "     ");

            String horizontalDivider = "____________________________________________________________\n";
            String output = builder.append(horizontalDivider)
                    .append("\n")
                    .append(messageWithIndent)
                    .append("\n")
                    .append(horizontalDivider)
                    .toString();

            System.out.println(output);
        }
    }

    /**
     * Opens the output channel.
     */
    @Override
    public void startOutputChannel() throws UiInitializationException {
        this.isOpen = true;

        try {
            this.displayOutput(GREETING);
        } catch (UiException e) {
            throw new UiInitializationException("Something went wrong with the output channel.");
        }
    }

    /**
     * Closes the output channel.
     */
    @Override
    public void stopOutputChannel() {
        this.isOpen = false;
    }
}

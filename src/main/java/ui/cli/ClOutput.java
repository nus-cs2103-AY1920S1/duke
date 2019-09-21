package ui.cli;

import error.ui.UiException;
import ui.DukeOutput;

/**
 * Encapsulates the command line as a DukeOutput output channel for the application.
 */
public class ClOutput extends DukeOutput {
    private static String HORIZONTAL_DIVIDER = "    ____________________________________________________________\n";
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

            String output = builder.append(HORIZONTAL_DIVIDER)
                    .append("\n")
                    .append(messageWithIndent)
                    .append("\n")
                    .append(HORIZONTAL_DIVIDER)
                    .toString();

            System.out.println(output);
        }
    }

    /**
     * Opens the output channel.
     */
    @Override
    public void startOutputChannel() {
        this.isOpen = true;
    }

    /**
     * Closes the output channel.
     */
    @Override
    public void stopOutputChannel() {
        this.isOpen = false;
    }
}

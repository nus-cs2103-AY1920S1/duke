package ui.cli;

import ui.output.OutputHandler;

/**
 * Command line output channel.
 */
public class ClOutput implements OutputHandler {
    private static String HORIZONTAL_DIVIDER = "    ____________________________________________________________\n";
    private static ClOutput singleton;

    private ClOutput() {

    }

    /**
     * Returns an instance of ClOutput.
     * @return ClOutput instance
     */
    public static ClOutput getInstance() {
        if (singleton == null) {
            singleton = new ClOutput();
        }

        return singleton;
    }

    @Override
    public void display(String message) {
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

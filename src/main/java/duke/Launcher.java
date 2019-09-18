package duke;

import duke.cli.DukeCli;
import duke.gui.DukeGui;
import javafx.application.Application;

import java.util.Set;

/**
 * Entry point of the Duke application. Launches either CLI or GUI per user input.
 */
public final class Launcher {
    private static final Set<String> VALID_ARGS = Set.of("cli", "gui");

    // For static use only
    private Launcher() {
    }

    /**
     * Launches the Duke application, or exit if the argument is invalid.
     *
     * @param args "cli" or "gui", defaults to "gui" if empty
     */
    public static void main(String[] args) {
        if (!hasValidArgs(args)) {
            printUsageAndExit();
        }

        String mode = getMode(args);
        if (mode.equals("cli")) {
            launchCli();
        } else {
            launchGui();
        }
    }

    private static boolean hasValidArgs(String[] args) {
        return args.length == 0 || args.length == 1 && VALID_ARGS.contains(args[0]);
    }

    private static String getMode(String[] args) {
        if (args.length == 1 && args[0].equals("cli")) {
            return "cli";
        } else {
            // Default to launching GUI
            return "gui";
        }
    }

    private static void printUsageAndExit() {
        System.err.println("Usage: java -jar duke-0.x.jar [cli]");
        System.exit(1);
    }

    private static void launchGui() {
        Application.launch(DukeGui.class);
    }

    private static void launchCli() {
        DukeCli cli = new DukeCli();
        cli.run();
    }
}

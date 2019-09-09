package duke;

import duke.cli.DukeCli;
import duke.gui.DukeGui;
import javafx.application.Application;

import java.util.Set;

public class Launcher {
    private static final Set<String> VALID_ARGS = Set.of("cli", "gui");

    /**
     * Launches the Duke application.
     *
     * @param args determines whether to launch cli or gui
     */
    public static void main(String[] args) {
        if (args.length != 1 || !VALID_ARGS.contains(args[0])) {
            printUsageAndExit();
        } else if (args[0].equals("cli")) {
            launchCli();
        } else if (args[0].equals("gui")) {
            launchGui();
        } else {
            // Should not reach here
            assert false;
        }
    }

    private static void printUsageAndExit() {
        System.err.println("Usage: java Launcher ( cli | gui )");
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

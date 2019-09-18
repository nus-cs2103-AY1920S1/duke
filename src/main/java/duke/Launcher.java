package duke;

import duke.gui.Main;
import javafx.application.Application;

import java.util.List;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs the appropriate Duke based on the command line arguments.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (List.of(args).contains("cmdline")) {
            CmdDuke.main(args);
        } else {
            Application.launch(Main.class, args);
        }
    }
}

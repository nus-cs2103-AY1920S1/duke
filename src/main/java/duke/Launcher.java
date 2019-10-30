package duke;

import duke.gui.Main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main method of the Launcher class.
     * @param args Unused
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

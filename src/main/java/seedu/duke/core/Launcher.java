package seedu.duke.core;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main entry point for Duke GUI.
     *
     * @param args Main entry point for Duke GUI.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

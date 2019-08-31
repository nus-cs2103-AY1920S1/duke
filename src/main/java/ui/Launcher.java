package ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to run the system.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
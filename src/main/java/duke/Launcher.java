package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method. Initiates a full run of a chat bot.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
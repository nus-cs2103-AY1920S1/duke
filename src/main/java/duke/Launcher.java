package duke;

import javafx.application.Application;

/**
 * A launcher class being the main entry point of the application to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

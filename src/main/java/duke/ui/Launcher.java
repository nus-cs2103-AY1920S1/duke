package duke.ui;

import duke.Duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * @author Lim Yong Shen, Kevin
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}

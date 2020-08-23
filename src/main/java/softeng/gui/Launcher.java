package softeng.gui;

import javafx.application.Application;
import softeng.duke.Duke;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}

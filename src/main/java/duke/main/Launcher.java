package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Starts the duke application.
     * @param args the array of arguments from the command line
     */
    public static void main(String[] args) {
        Application.launch(duke.main.Main.class, args);
    }

}

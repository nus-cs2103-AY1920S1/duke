package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        //new Duke("C:\\duke\\src\\main\\java\\data\\duke.txt");
        Application.launch(Main.class, args);
    }
}
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method which launches the GUI and start up the main class.
     *
     * @param args takes in the arguments from sys
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
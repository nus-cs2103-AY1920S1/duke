import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application via the Main class and calls the start method.
     * @param args Passed to main class
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
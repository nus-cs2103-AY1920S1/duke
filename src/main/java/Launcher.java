import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Main entry into program
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
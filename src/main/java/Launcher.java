import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues and launch application.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
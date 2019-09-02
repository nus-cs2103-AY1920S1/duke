import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.err.println("hello");
        Application.launch(Gui.class, args);
    }
}

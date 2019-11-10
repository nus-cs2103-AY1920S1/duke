import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the Duke program.
     *
     * @param args the main method args argument
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
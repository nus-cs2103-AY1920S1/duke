import javafx.application.Application;
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println("Launching");
        Application.launch(Main.class, args);
        System.out.println("Launched");
    }
}
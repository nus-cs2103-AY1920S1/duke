import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
//Solution below adapted from https://github.com/nus-cs2103-AY1920S1/duke
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main method. This will be called when the program launches.
     * 
     * @param args The arguments passed to the program through the commmand line
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
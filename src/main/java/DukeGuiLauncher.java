import duke.DukeGui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */

public class DukeGuiLauncher {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
import javafx.application.Application;
import seedu.duke.Duke;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        //Application.launch(Duke.class, "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt");
        Application.launch(Duke.class, args);
    }
}

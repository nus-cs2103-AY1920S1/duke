import javafx.application.Application;
import seedu.duke.Main;

public class Launcher {
    /**
     * Default main function to run <code>Duke</code> chat bot. Calling this function will start <code>Duke</code>
     * with default task data at 'data/task.txt'. If there is no such file, the program will create an empty file.
     *
     * @param args Arguments that are provided upon running the program. Those arguments will not give
     *             any effect towards the program execution.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

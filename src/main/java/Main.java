import duke.Duke;
import duke.exception.DukeIoException;

public class Main {

    /**
     * Runs the {@link Duke} application.
     *
     * @param args Arguments passed through the terminal
     */
    public static void main(String[] args) {
        try {
            (new Duke()).run();
        } catch (DukeIoException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }

}

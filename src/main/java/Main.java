import duke.DukeCli;
import duke.exception.DukeIoException;

public class Main {

    /**
     * Runs the {@link DukeCli} application.
     *
     * @param args Arguments passed through the terminal
     */
    public static void main(String[] args) {
        try {
            (new DukeCli()).run();
        } catch (DukeIoException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }

}

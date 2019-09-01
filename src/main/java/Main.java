import duke.Duke;
import duke.exception.DukeIOException;

public class Main {

    public static void main(String[] args) {
        try {
            (new Duke()).run();
        } catch (DukeIOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }

}

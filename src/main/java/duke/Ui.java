package duke;

import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * Ui class for user interaction and to manage interface.
 *
 */
public class Ui {

    /**
     * Ui Constructor.
     */
    public Ui() {
    }

    /**
     * Error printer if duke.DukeException is encountered.
     *
     * @param e Caught duke.DukeException.
     * @return The error that is faced by Duke.
     */
    public String showLoadingError(DukeException e) {
        return e.toString();
    }

    /**
     *  Returns a String response to the given input to the interface.
     *
     * @param args Iteration of string inputs that each represent a line to be shown.
     * @return String response from the GUI to the user.
     */
    public String generateResponse(String... args) {
        String content = format("%s", Stream.of(args).map(s -> s + "\n")
                .reduce((x,y) -> x + y).orElse(""));
        return content;
    }
}

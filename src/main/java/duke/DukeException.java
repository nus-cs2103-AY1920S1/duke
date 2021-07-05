package duke;

import duke.ui.MainWindow;

public class DukeException extends Exception {

    public static MainWindow mw;

    /**
     * Creates a Duke Exception object.
     *
     * @param message Error message to print.
     */
    public DukeException(String message) {
        assert message.length() > 0 : "Message cannot be empty";
        mw.setDukeMessage("\u2639 OOPS!!! " + message);
    }
}

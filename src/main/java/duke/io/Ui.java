package duke.io;

import java.util.ArrayList;

/**
 * User Interface for Duke.
 */
public class Ui {
    private StringBuilder dukeOut;

    /**
     * Constructor creates new scanner to read System-in.
     */
    public Ui() {
        dukeOut = new StringBuilder();
        out("Hello! I'm Duke");
    }

    public void out(String output) {
        assert output != null : "Empty Output";
        dukeOut.append(output).append("\n");
    }

    public StringBuilder getDukeOut() {
        return dukeOut;
    }

    public void clear() {
        dukeOut = new StringBuilder();
    }

    /**
     * Prints out list of tasks.
     *
     * @param list List of items
     */
    public void list(ArrayList list) {
        if (list.size() == 0) {
            out("List is empty!");
            return;
        }
        out("Here are the items in your list:");
        for (int i = 0; i < list.size(); i++) {
            out((i + 1) + ". " + list.get(i));
        }
    }
}

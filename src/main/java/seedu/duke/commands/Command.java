package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.trackables.Task;

import java.util.List;

public abstract class Command {

    static final String HORIZONTAL_LINE = "______________________________"
            + "______________________________";

    public void execute(List<Task> tasks) throws DukeException {

    }

    /**
     * Prints out the message.
     * @param messages Array of messages to show.
     */
    protected void echo(String... messages) {
        printLine();
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        printLine();
    }

    /**
     * Print horizontal line.
     */
    protected void printLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

}

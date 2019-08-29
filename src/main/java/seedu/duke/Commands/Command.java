package seedu.duke.Commands;

import seedu.duke.Task;

import java.util.List;

public abstract class Command {

    static final String HORIZONTAL_LINE = "______________________________"
            + "______________________________";

    /**
     * Executes the command.
     * @return Returns the message to be printed out.
     */
    public void execute(List<Task> tasks) {

    }

    protected void echo(String[] message) {
        printLine();
        for (int i = 0; i < message.length; i++) {
            System.out.println("\t" + message[i]);
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

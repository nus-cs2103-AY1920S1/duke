package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a list command.
 * A <code>ListCommand</code> object corresponds to a command to list all <code>Task</code> objects
 * in a <code>TaskList</code> object.
 */
public class ListCommand extends Command {

    /**
     * Constructor for <code>ListCommand</code>.
     */
    public ListCommand() {
        super();
    }

    /**
     * Calls the method in the <code>Ui</code> object to output the <code>String</code> representations of
     * the <code>Task</code> objects in <code>TaskList</code>.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("\t Here are the tasks in your list: ");
        for (int i = 0; i < tasks.getListSize(); i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}

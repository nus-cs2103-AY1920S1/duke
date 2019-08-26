package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a find command.
 * A <code>FindCommand</code> object corresponds to a command to find tasks with a given keyword in
 * a <code>TaskList</code> object.
 * from a <code>TaskList</code>.
 */
public class FindCommand extends Command {
    protected String details;

    /**
     * Constructor for <code>FindCommand</code>.
     * @param details Details for find command, which includes the keyword that is being searched for.
     */
    public FindCommand(String details) {
        super();
        this.details = details;
    }

    /**
     * Searches for <code>Task</code> objects with given keyword in their description in the <code>TaskList</code>.
     * Calls the method in the <code>Ui</code> object to output the <code>String</code> representations of
     * the matching <code>Task</code> objects in <code>TaskList</code>.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     * @throws DukeException If non-existent index is provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printMessage("\t Here are the matching tasks in your list: ");
        if (details.length() == 0) {
            for (int i = 0; i < tasks.getListSize(); i++) {
                System.out.println("\t " + (i + 1) + ". " + tasks.getTask(i));
            }
        } else {
            for (int i = 0; i < tasks.getListSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(details)) {
                    System.out.println("\t " + (i + 1) + ". " + tasks.getTask(i));
                }
            }
        }
    }

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the keyword for the <code>FindCommand</code>.
     * @return Details for command.
     */
    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof FindCommand) {
            FindCommand obj = (FindCommand) o;
            return obj.getDetails().equals(details);
        } else {
            return false;
        }
    }
}

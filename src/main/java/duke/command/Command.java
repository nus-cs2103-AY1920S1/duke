package duke.command;

import duke.exception.DukeException;
import duke.component.Ui;
import duke.component.TaskList;
import duke.database.Storage;

/**
 * This Command class is the abstract class with execute method
 * and isExit() method.
 *
 * @author TeoShyanJie
 *
 */
public abstract class Command {
    /** Task enter by user. */
    protected String input;

    /** The type of task enter by user. */
    protected String type;

    /** The exit status. */
    protected boolean exit = false;

    /**
     * The Constructor of Abstract Class Command.
     * @param input Task enter by user.
     * @param type The type of task.
     */
    public Command(String input, String type) {
        this.input = input;
        this.type = type;
    }

    /**
     * Abstract method for executed method.
     * @param tasks List of task.
     * @param ui Ui of Duke Program.
     * @param storage Database of the Duke Program.
     * @throws DukeException If execute method fail to run the intended method.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Exit the Duke Program.
     * @return True to continue or False to exit.
     */
    public Boolean isExit() {
        return exit;
    }
}
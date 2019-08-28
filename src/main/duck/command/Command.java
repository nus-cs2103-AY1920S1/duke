package duck.command;

import duck.util.DukeException;
import duck.util.TaskList;
import duck.util.Storage;
import duck.util.Ui;

/**
 * Command is the abstract base class for all executable commands.
 * A Command object encapsulates the information about whether a command should be treated as a termination of user
 * input. Besides, the abstract method <code>execute</code> needs to be specified about what actions to take according
 * to certain commands.
 *
 * @author  Zhnag Xiaoyu
 */

public abstract class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes some actions to process the task list, show users information, and / or save data according to
     * specified commands.
     *
     * @param taskList        the task list that provides information about users' current tasks and to be modified
     * @param ui              the <code>Ui</code> object to handle input and output
     * @param storage         the <code>Storage</code> object to load and record data
     * @throws DukeException  the <code>DukeException</code> that may be thrown during command execution
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Compares two commands based on their types.
     *
     * @param obj  the object to be compared
     * @return     <code>true</code> if two commands are of the same type;
     *             <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass());
    }
}

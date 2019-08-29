package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;

/**
 * An abstract class meant to be inherited by commands in Duke
 * e.g., <code>list</code>, <code>todo</code>, <code>done</code> commands.
 */
public abstract class Command {

    String commandInformation;

    /**
     * Default constructor for Command class
     */
    public Command() {
    }

    /**
     * Alternative constructor for Command class
     *
     * @param commandInformation Information related to command
     *                           e.g., <code>taskName</code>,
     *                           <code>taskNumber</code>,
     *                           <code>deadLineDate</code>
     */
    public Command(String commandInformation) {
        this.commandInformation = commandInformation;
    }


    /**
     * Executes the command tied to each respective sub-class
     * and reads result of executed command into preset task.txt file as required
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws DukeException if error related to Duke commands occurs
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;


    /**
     * Informs the caller if the command should result in program termination
     *
     * @return A boolean specifying if program should terminate after command execution
     */
    public abstract boolean isExit();


}

package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Command containing method for marking Task from TaskList as done.
 */
public class DoneCommand extends Command {
    /**
     * Constructor for DoneCommand without parameters.
     */
    public DoneCommand() {
        this("");
    }

    /**
     * Constructor for DoneCommand with String parameter.
     * 
     * @param fullCommand Input entered by user.
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    /**
     * Returns a DoneCommand as initialized by the constructor.
     * 
     * @param fullCommand Input entered by user.
     */
    public Command clone(String fullCommand) {
        return new DoneCommand(fullCommand);
    }

    /**
     * Marks a Task from the TaskList as done.
     *
     * @param tasks TaskList to delete Task from.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int itemId = Parser.parseDone(this.fullCommand);
        try {
            tasks.markAsDone(itemId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no item " + itemId + ".");
        }
        ui.printResponse("Nice! I've marked this task as done: \n  "
                + tasks.get(itemId).toString());
    }

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return false so program does not exit.
     */
    public boolean isExit() {
        return false;
    }
}
package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Command containing method for deleting Task from TaskList.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand without parameters.
     */
    public DeleteCommand() {
        this("");
    }

    /**
     * Constructor for DeleteCommand with String parameter.
     * 
     * @param fullCommand Input entered by user.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    /**
     * Returns a DeleteCommand as initialized by the constructor.
     * 
     * @param fullCommand Input entered by user.
     */
    public Command clone(String fullCommand) {
        return new DeleteCommand(fullCommand);
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param tasks TaskList to delete Task from.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int itemId = Parser.parseDelete(this.fullCommand);
        try {
            Task item = tasks.remove(itemId);
            ui.printResponse("Noted. I've removed this task:  \n  "
                + item.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no item " + itemId + ".");
        }   
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
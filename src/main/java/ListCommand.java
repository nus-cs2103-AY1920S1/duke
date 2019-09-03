package duke.command;

import duke.Ui;
import duke.Storage;
import duke.TaskList;

/**
 * Command containing method for listing Tasks in TaskList.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand without parameters.
     */
    public ListCommand() {
        this("");
    }

    /**
     * Constructor for ListCommand with String parameter.
     * 
     * @param fullCommand Input entered by user.
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    /**
     * Returns a ListCommand as initialized by the constructor.
     * 
     * @param input Input entered by user.
     */
    public Command clone(String fullCommand) {
        return new ListCommand(fullCommand);
    }

    /**
     * List Tasks in TaskList.
     *
     * @param tasks TaskList of program.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage of program.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the tasks in your list:\n" 
                + tasks.toString());
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
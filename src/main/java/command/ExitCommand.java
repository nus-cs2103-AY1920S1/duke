package duke.command;

import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;

/**
 * Command containing method for exiting program.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand without parameters.
     */
    public ExitCommand() {
        this("");
    }

    /**
     * Constructor for ExitCommand with String parameter.
     * 
     * @param fullCommand Input entered by user.
     */
    public ExitCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    /**
     * Returns an ExitCommand as initialized by the constructor.
     * 
     * @param fullCommand Input entered by user.
     */
    public Command clone(String fullCommand) {
        return new ExitCommand(fullCommand);
    }
    
    /**
     * Exits program.
     *
     * @param tasks TaskList of program.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage of program.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return false so program does not exit.
     */
    public boolean isExit() {
        return true;
    }
}
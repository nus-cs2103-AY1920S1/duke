package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Command containing method for adding Task to TaskList.
 */
public class AddCommand extends Command {
    /**
     * Constructor for AddCommand without parameters.
     */
    public AddCommand() {
        this("");
    }

    /**
     * Constructor for AddCommand with String parameter.
     * 
     * @param fullCommand Input entered by user.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    /**
     * Returns an AddCommand as initialized by the constructor.
     * 
     * @param input Input entered by user.
     */
    public Command clone(String fullCommand) {
        return new AddCommand(fullCommand);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param tasks TaskList to add Task to.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = Parser.parseTask(this.fullCommand);
        tasks.add(task);
        ui.printResponse("Got it. I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list.");
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
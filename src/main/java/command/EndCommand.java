package command;

<<<<<<< .merge_file_a02180
import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class EndCommand extends Command {
=======
import task.TaskList;
import duke.UserInterface;
import duke.Storage;

/**
 * Specifies the 'end' action to end the session.
 */
public class EndCommand extends Command {
    /**
     * Constructs the End Command object by indicating the command is for exit.
     * Sets isExit to TRUE by default.
     */
>>>>>>> .merge_file_a09240
    public EndCommand() {
        super.isExit = true;
    }

<<<<<<< .merge_file_a02180
=======
    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
>>>>>>> .merge_file_a09240
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //display goodbye message
        System.out.println("\tBye. Hope to see you again soon!");
    }
}

package command;


import task.TaskList;
import ui.UserInterface;
import duke.Storage;

/**
 * Specifies the 'end' action to end the session.
 */
public class EndCommand extends Command {
    /**
     * Constructs the End Command object by indicating the command is for exit.
     * Sets isExit to TRUE by default.
     */
    public EndCommand() {
        super.isExit = true;
    }

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //display goodbye message
        ui.goodbye();
    }
}

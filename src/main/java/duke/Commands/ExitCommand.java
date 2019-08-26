package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;
import java.io.IOException;

/**
 * This is the Command subclass to exit duke.
 * @Extends duke.Commands.Command
 */
public class ExitCommand extends Command {

    /**
     * Constructor of the class, nothing special.
     */
    public ExitCommand() {}

    /**
     * This method calls the target task list to save itself to a file and let the target user end
     *     to print out goodbye message to the user.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException If IOException occurs in the saving process.
     */
    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        try{
            tl.rewrite();
            ui.showExitMessage();
        } catch (IOException e) {
            ui.showExitMessage();
            throw new DukeException("Unable to rewrite task list. Modification this time cannot be saved.");
        }

    }

    /**
     * Determines whether this is an exit command.
     * @return boolean true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

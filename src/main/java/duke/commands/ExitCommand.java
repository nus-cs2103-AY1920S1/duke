package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;
import java.io.IOException;

/**
 * This is the specific command to exit duke and save the current task list into a text file.
 */
public class ExitCommand extends Command {

    public ExitCommand() {

    }

    /**
     * Call the task list to save the new modification into the task file.
     * Call the user interface to generate a goodbye message as a String.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The goodbye message.
     * @throws DukeException when an IOException is raised while rewriting the task file.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        try {
            tl.rewrite();
            return ui.showExitMessage();
        } catch (IOException e) {
            throw new DukeException("Unable to rewrite task list. Modification this time cannot be saved.");
        }
    }

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;

/**
 * This is the specific class to handle the invalid commands.
 * Invalid commands only refers to the first key word of the user's command cannot be recognized.
 */
public class FakeCommand extends Command {

    /**
     * Constructor of the class, nothing special.
     */
    public FakeCommand() {

    }

    /**
     * This method directly throw out an DukeException to tell the user that this command is invalid.
     *
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException Throw it anyway since the command is invalid.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        throw new DukeException("Please input a valid command.");
    }

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

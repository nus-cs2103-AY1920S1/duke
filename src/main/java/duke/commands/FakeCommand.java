package duke.commands;

import duke.directprocessor.TaskList;
import duke.directprocessor.Ui;
import duke.DukeException;

/**
 * This is the specific class to handle the invalid commands.
 * Invalid commands only refers to the first key word of the user's command cannot be recognized.
 */
public class FakeCommand extends Command {

    public FakeCommand() {

    }

    /**
     * This method directly throws out an DukeException to tell the system that this command is invalid.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return Since the command is itself invalid, nothing can actually be returned.
     * @throws DukeException every time this method runs.
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

package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;

/**
 * This is the Command subclass particularly handle the invalid commands.
 * Invalid commands only refers to the first key word of the user's command cannot be recognized.
 */
public class FakeCommand extends Command {

    /**
     * Contructor of the class, nothing special
     * @Extends duke.Commands.Command
     */
    public FakeCommand() {}

    /**
     * This method directly throw out an DukeException to tell the user that this command is invalid.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        throw new DukeException("Please input a valid command.");
    }

    /**
     * Determines whether this is an exit command.
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

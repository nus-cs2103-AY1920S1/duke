package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * JokeCommand class which tells the user that he is a joke when executed.
 */
public class JokeCommand extends Command {

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        return new CommandResult("you");
    }
}

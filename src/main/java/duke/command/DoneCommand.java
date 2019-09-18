package duke.command;

/**
 * Encapsulates a DoneCommand object in charge of marking the target task as done.
 */

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {

    /**
     * The constructor is inherited from Command class.
     * @param fullCommand String of valid, full command input
     */
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    /**
     * Marks the task with given index to be done and updates the storage file.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage
     * @throws IOException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Parser parser = new Parser(fullCommand);
        String message = tasks.doneTask(parser.getIndices());
        storage.updateFile(tasks);
        return message;

    }
}

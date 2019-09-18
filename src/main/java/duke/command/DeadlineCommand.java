package duke.command;

/**
 * Encapsulates a DeadlineCommand object in charge of adding a deadline task into the task list.
 */

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;

import java.io.IOException;

public class DeadlineCommand extends Command {

    /**
     * The constructor is inherited from Command class.
     * @param fullCommand String of valid, full command input
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    /**
     * Adds a deadline task into the taskList and updates file in storage.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage
     * @throws IOException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Parser parser = new Parser(fullCommand);
        String message = tasks.addDeadline(parser.getActivityNameWithTime(),
            parser.getDeadline(), false);
        storage.updateFile(tasks);
        return message;
    }
}
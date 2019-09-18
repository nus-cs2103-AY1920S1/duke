package duke.command;

/**
 * Encapsulates a TodoCommand object in charge of adding a todo task into the tasklist.
 */

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;

import java.io.IOException;

public class TodoCommand extends Command {

    /**
     * The constructor is inherited from Command class.
     * @param fullCommand String of valid, full command input
     */
    public TodoCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    /**
     * Adds a todo task into the taskList and updates file in storage.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage
     * @throws IOException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Parser parser = new Parser(fullCommand);
        String message = tasks.addTodo(parser.getActivityNameWithoutTime(),
            false);
        storage.updateFile(tasks);
        return message;
    }
}
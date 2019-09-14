package duke.command;

/**
 * Encapsulates a DeleteCommand object in charge of deleting the target task from the list.
 */

import duke.Parser;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    /**
     * The constructor is inherited from Command class.
     * @param fullCommand String of valid, full command input
     */
    public DeleteCommand (String fullCommand) {
        super(fullCommand);
    }

    @Override
    public
    /**
     * Deletes the task with given index from the list and updates the storage file.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage
     * @throws IOException
     */
    String execute(TaskList tasks, Storage storage) throws IOException {
        Parser parser = new Parser(fullCommand);
        String message = tasks.deleteTask(parser.getIndex());
        storage.updateFile(tasks);
        return message;
    }
}
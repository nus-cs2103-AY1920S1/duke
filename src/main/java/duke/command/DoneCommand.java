package duke.command;

/**
 * Encapsulates a DoneCommand object in charge of marking the target task as done.
 */

import duke.Parser;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {

    /**
     * The constructor is inherited from Command class.
     * @param fullCommand String of valid, full command input
     */
    public DoneCommand (String fullCommand) {
        super(fullCommand);
    }

    @Override
    public
    /**
     * Marks the task with given index to be done and updates the storage file.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage
     * @throws IOException
     */
    String execute(TaskList tasks, Storage storage) throws IOException {
        Parser parser = new Parser(fullCommand);
        int[] range = parser.getIndices();
        String message = "Nice! I've marked the following task(s) as done: \n  ";
        for (int i : range) {
            message += tasks.doneTask(i);
            message += "\n  ";
        }
        storage.updateFile(tasks);
        return message;
    }
}

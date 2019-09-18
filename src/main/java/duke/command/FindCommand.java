package duke.command;

import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a FindCommand object in charge of finding tasks which contains the keyword
 * in the existing tasks list.
 */

public class FindCommand extends Command {

    /**
     * The constructor is inherited from Command class.
     * @param fullCommand String of valid, full command input
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    /**
     * Lists tasks that contains the keyword in the tasks list.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage
     */
    public String execute(TaskList tasks, Storage storage) {
        Parser parser = new Parser(fullCommand);
        return tasks.findTask(parser.getKeyword());
    }
}
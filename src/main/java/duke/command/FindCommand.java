package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a FindCommand object in charge of finding tasks which contains the keyword
 * in the existing tasks list.
 */

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a FindCommand object.
     * @param fullCommand String of full, valid command
     * @param keyword String of keyword
     */
    public FindCommand(String fullCommand, String keyword) {
        super(fullCommand);
        this.keyword = keyword;
    }

    @Override
    /**
     * Lists tasks that contains the keyword in the tasks list.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     */
    public String execute(TaskList tasks, Storage storage) {
        return tasks.findTask(keyword);
    }
}
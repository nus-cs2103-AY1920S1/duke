package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a command to find tasks according to a keyword.
 */
public class FindCommand extends Command {

    /** String to be searched for in the Find command */
    private String keyword;

    /**
     * Creates a FindCommand object that stores a keyword to be searched.
     *
     * @param keyword Keyword or phrase that is searched for in the tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find a keyword amongst a TaskList of Task objects
     *
     * @param tasks TaskList on which the Command should be executed on.
     */
    public String execute(TaskList tasks, Storage storage) {
        return tasks.findTasks(keyword);
    }
}

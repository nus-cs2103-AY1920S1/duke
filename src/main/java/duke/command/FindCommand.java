package duke.command;

import duke.task.TaskList;

/**
 * Class representing a command that searches the current TaskList with a keyword.
 *
 * @see Command
 */
public class FindCommand extends Command {

    public static final String KEYWORD = "find";

    private String searchWord;

    /**
     * Returns a FindCommand object that will attempt to search the current TaskList
     * when executed.
     *
     * @param searchWord the keyword to be used for searching
     */
    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * Returns a list of all matching tasks. As long as the task description contains
     * a substring that matches the keyword, it will be counted as a positive result.
     * The search is case-insensitive (ie. matches words irregardless of differences in case).
     *
     * @return result feedback of the command to be printed to the user
     */
    @Override
    public String execute() {
        TaskList results = this.taskList.getTaskSubsetMatching(searchWord);
        if (results.getSize() == 0) {
            return "I'm sorry, I didn't find any tasks containing the phrase\n'"
                    + searchWord
                    + "' ...";
        } else {
            return "Here are all the tasks in your list that matches the query '" + this.searchWord + "':\n"
                    + ui.indentMessage(results.toString());
        }
    }
}

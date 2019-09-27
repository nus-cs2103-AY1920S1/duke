package seedu.duke.commands;

import seedu.duke.storage.TaskList;
import seedu.duke.ui.StringStore;

/**
 * Abstraction of the FindCommand.
 */
public class FindCommand extends Command {

    private String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Looks for any tasks that contain the string in {@code pattern} and builds a list of all matches
     * then returns the results.
     * @param tasks The current TaskList instance.
     */
    @Override
    public String execute(TaskList tasks) {

        TaskList results = tasks.findByDescription(pattern);
        StringBuilder response = new StringBuilder();

        response.append(StringStore.FIND_SUCCESSFUL);

        // Inform if no results found, then return to caller.
        if (results.size() == 0) {
            return StringStore.FIND_UNSUCCESSFUL + "\"" + pattern + "\"";
        }

        response.append(results.getListAsString());
        return response.toString();
    }
}

package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

public class FindCommand extends Command {

    private String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String execute(TaskList tasks) {

        TaskList results = tasks.findByDescription(pattern);
        StringBuilder response = new StringBuilder();

        response.append("Here are the matching tasks in your list:\n");

        // Inform if no results found, then return to caller.
        if (results.size() == 0) {
            return "There were no tasks with the following contents " + "\"" + pattern + "\"";
        }

        response.append(results.printAll());
        return response.toString();
    }
}

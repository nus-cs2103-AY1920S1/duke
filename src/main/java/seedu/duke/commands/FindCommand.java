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
    public void execute(TaskList tasks) throws InvalidArgumentException {

        TaskList results = tasks.findByDescription(pattern);
        String[] message = new String[results.size() + 1];
        message[0] = "Here are the matching tasks in your list:";;

        // Inform if no results found, then return to caller.
        if (results.size() == 0) {
            Ui.printMessages("There were no tasks with the following contents ", "\"" + pattern + "\'");
            return;
        }

        for (int i = 0; i < results.size(); i++) {
            message[i+1] = "\t" + (i + 1) + "." + results.get(i).toString();
        }
        Ui.printMessages(message);
    }
}

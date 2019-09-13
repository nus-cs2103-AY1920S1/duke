package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

public class FindCommand extends Command {
    String keyword;
    boolean hasFound = false;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * This method is used to find all related tasks given
     * a keyword from user input.
     *
     * @param tasks   the TaskList object to be used in this command
     * @param ui      the Ui object to be used in this command
     * @param storage the Storage object to be used in this command
     * @return duke response after finding
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert keyword != null;
        String output = ui.print("Here are the matching tasks in your list:");
        assert tasks.getList() != null;
        for (Task t : tasks.getList()) {
            if (t.toString().contains(keyword)) {
                output += ("\n" + ui.print(t.toString()));
                hasFound = true;
            }
        }
        if (!hasFound) {
            return "Sorry, keyword not found!";
        }

        return output;
    }

}

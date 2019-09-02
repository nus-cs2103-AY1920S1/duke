package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

public class FindCommand extends Command {
    String keyword;
    boolean foundAny = false;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * This method is used to find all related tasks given
     * a keyword from user input.
     *
     * @return duke response after finding
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = ui.print("Here are the matching tasks in your list:");
        for (Task t : tasks.getList()) {
            if (t.toString().contains(keyword)) {
                output += ("\n" + ui.print(t.toString()));
                 foundAny = true;
            }
        }
        if (!foundAny) {
            return "Sorry, keyword not found!";
        }

        return output;
    }

}

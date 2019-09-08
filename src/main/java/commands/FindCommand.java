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
        assert foundAny = false;
        assert keyword != null;
        String output = ui.print("Here are the matching tasks in your list:");
        assert tasks.getList()!= null;
        for (Task t : tasks.getList()) {
            if (t.toString().contains(keyword)) {
                output += ("\n" + ui.print(t.toString()));
                foundAny = true;
            }
            assert !t.toString().contains(keyword);
        }
        if (!foundAny) {
            return "Sorry, keyword not found!";
        }
        assert foundAny; //foundAny must be true

        return output;
    }

}

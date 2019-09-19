package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

/**
 * This command gives users a way to find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.sendMessage("Here are the matching tasks in your list: ");
        for (int i = 0; i < tasklist.size(); i ++) {
            String task = tasklist.get(i).toString();
            boolean isMatch = task.contains(keyword);
            if (isMatch) {
                ui.sendMessage((i + 1) + "." + task);
            }
        }
    }

}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Finds and lists all tasks in the task list whose description contains the specified keyword.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = tasks.filter(keyWord);
        ui.showFindMsg(result);
    }
}

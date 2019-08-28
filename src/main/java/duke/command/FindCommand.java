package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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

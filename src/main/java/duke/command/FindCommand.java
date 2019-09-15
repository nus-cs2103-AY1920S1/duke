package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    public String keyWord;

    public FindCommand (String keyWord) {
        this.keyWord = keyWord.trim();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.findKeyWordInList(this.keyWord));
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    protected final String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showFindMessage(taskList, keyword);
    }
}

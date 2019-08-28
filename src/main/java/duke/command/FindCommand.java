package duke.command;

import duke.core.TaskList;
import duke.core.Ui;
import duke.core.Storage;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSearchResults(tasks, keyword);
    }
}

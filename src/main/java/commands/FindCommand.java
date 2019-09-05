package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.matchingList();
        tasks.getList(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

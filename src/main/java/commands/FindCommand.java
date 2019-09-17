package commands;

import storage.Storage;
import ui.Ui;
import tasks.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return (ui.matchingList() + "\n" + (tasks.findListEntry(keyword)));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

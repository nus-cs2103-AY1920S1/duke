package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    String kw;

    public FindCommand(String kw) {
        this.kw = kw;
    }

    /**
     * Finds task
     * @param tasks
     * @param ui       ui
     * @param storage  storage
     * @return Found task
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        String taskMessage = tasks.find(kw);
        return ui.getFoundMessage(taskMessage);
    }

    public boolean isExit() {
        return false;
    }

}
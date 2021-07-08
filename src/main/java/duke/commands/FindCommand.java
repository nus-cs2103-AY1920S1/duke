package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.ui.UiInterface;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        ui.echoMatchingTasks(tasks.getMatchingTasks(keyword));
    }

}

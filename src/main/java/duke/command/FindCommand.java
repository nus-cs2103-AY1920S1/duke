package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(Tasklist list, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : list.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchList.add(task);
            } // End if.
        } // End for loop.
        ui.listFindMatches(matchList);
    }
}

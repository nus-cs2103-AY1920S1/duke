package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm){
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matches = new TaskList(tasks.searchTaskListForKeyword(this.searchTerm));
        matches.listAllTasks(ui);
    }
}

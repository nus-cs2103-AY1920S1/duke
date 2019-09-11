package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class SortCommand extends Command {

    public SortCommand() {
        super(false);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.sortByDescription(ui);
    }
}

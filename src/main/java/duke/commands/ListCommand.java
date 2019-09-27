package duke.commands;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        return ui.list(tasks);
    }

}

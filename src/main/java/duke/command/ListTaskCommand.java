package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListTaskCommand extends Command {

    public ListTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        ui.listTasks();
    }

}

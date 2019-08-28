package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand(String inputCommand) {
        super(inputCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printRecord(tasks.getTaskList());
    }
}

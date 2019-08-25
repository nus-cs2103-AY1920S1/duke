package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

public class CompleteTaskCommand extends Command {

    public CompleteTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        int taskIndex = dataParser.getTaskIndex();
        taskList.completeTask(taskIndex);
        ui.showCompletedTask(TaskList.getTask(taskIndex));
        storage.save();
    }

}

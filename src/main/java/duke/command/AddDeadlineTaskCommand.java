package duke.command;

import duke.task.*;
import duke.exception.*;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddDeadlineTaskCommand extends Command {

    public AddDeadlineTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        if (TaskList.getNumberOfTasks() >= 100) {
            throw new TooManyTasksException();
        }
        String[] deadlineData = dataParser.parseDeadlineData();
        dateParser.readInput(deadlineData[1]);
        String dateOutput = dateParser.convertDateToString();
        String nameOutput = deadlineData[0];
        int taskIndex = taskList.addDeadlineTask(nameOutput, dateOutput);
        ui.showAddedTask(TaskList.getTask(taskIndex));
        storage.save();
    }

}

package duke.command;

import duke.task.*;
import duke.exception.*;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddToDoTaskCommand extends Command {

    public AddToDoTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        if (TaskList.getNumberOfTasks() >= 100) {
            throw new TooManyTasksException();
        }
        String toDoData = dataParser.parseToDoData();
        int taskIndex = taskList.addTodoTask(toDoData);
        ui.showAddedTask(TaskList.getTask(taskIndex));
        storage.save();
    }

}

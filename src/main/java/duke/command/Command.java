package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.TooManyTasksException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

public class Command {

    public boolean isExit;

    public Command(boolean isExit) {
        this.isExit =  isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {

    }

}

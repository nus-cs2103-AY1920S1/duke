package duke.command;

import duke.task.*;
import duke.exception.*;
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

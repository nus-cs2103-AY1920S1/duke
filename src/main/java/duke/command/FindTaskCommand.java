package duke.command;

import java.util.ArrayList;
import duke.task.*;
import duke.exception.*;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

public class FindTaskCommand extends Command {

    public FindTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        String keyword = dataParser.findKeyWord();
        ArrayList<Integer> taskIndexes = taskList.findMatchingTasks(keyword);
        ui.showMatchingTasks(taskIndexes);
    }

}

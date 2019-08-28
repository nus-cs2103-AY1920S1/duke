package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends InputCommand {
    public FindCommand(String toFind) {
        super(toFind);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String toFind = getString();
        ui.showMatches(taskList.findTask(toFind));
    }
}

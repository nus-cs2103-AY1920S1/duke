package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.types.SortType;
import duke.ui.Ui;

public class SortCommand extends Command {
    SortType sortType;

    public SortCommand(String commandContent) throws DukeException {
        if (commandContent.isEmpty()) {
            throw new DukeException("Description of sort cannot be empty.");
        }

        if (commandContent.split(" ").length > 1) {
            throw new DukeException("Description of sort cannot have more than one word.");
        }

        try {
            sortType = SortType.valueOf(commandContent.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid sort type.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.sort(sortType);
        ui.showSortSuccessMsg(sortType);
    }
}

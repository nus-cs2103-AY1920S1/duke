package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.toString();
    }
}

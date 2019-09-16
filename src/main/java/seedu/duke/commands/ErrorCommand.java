package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

public class ErrorCommand extends Command {
    private DukeException exception;

    public ErrorCommand(DukeException dukeException) {
        this.exception = dukeException;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        throw exception;
    }
}

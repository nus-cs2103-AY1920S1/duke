package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.util.List;

public class ErrorCommand extends Command {
    private DukeException dukeException;

    public ErrorCommand(DukeException dukeException) {
        this.dukeException = dukeException;
    }

    @Override
    public void execute(List<Task> tasks) {
        Ui.printMessages(dukeException.toString());
    }
}

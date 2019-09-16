package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.trackables.Task;

import java.util.List;

public abstract class Command {

    public void execute(List<Task> tasks) throws DukeException {

    }

}

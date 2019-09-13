package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class UnknownCommand extends Command {

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stat,
                          Storage statStorage) throws DukeException {

        throw new DukeException(":-( OOPS!!! I'm sorry, but I don't know what that means :-(");

    }
}

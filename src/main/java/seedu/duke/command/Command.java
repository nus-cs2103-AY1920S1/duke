package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                                   Storage statStorage) throws DukeException, IOException;
}

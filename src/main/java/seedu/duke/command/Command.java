package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

/**
 * Command class to represent all commands which Duke can do.
 */
public abstract class Command {
    /**
     * Executes the logic of the Command.
     *
     * @param fullCommand Full String command entered by the User.
     * @param ui User Interface object.
     * @param tasks TaskList object.
     * @param taskStorage Storage object for tasks.
     * @param stats Statistic object.
     * @param statStorage Storage object for stats.
     * @return String sequence to be printed to the User.
     * @throws DukeException
     * @throws IOException
     */
    public abstract String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                                   Storage statStorage) throws DukeException, IOException;
}

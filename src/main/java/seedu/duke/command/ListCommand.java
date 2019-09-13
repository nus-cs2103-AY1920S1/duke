package seedu.duke.command;

import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) {

        stats.incrementTotalCommandsExecuted();

        return ui.getListSequence(tasks);

    }

}

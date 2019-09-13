package seedu.duke.command;

import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class FindCommand extends Command {

    public FindCommand() {

    }

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) {

        stats.incrementTotalCommandsExecuted();

        String taskToFind = Parser.getFindTask(fullCommand);

        TaskList similarTasks = tasks.findSimilarTasks(taskToFind);

        return ui.getFoundTasks(similarTasks);

    }

}

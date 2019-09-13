package seedu.duke.command;

import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class DoneCommand extends Command {

    public DoneCommand() {

    }

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) {

        stats.incrementTotalCommandsExecuted();

        int taskNum = Parser.getFinishedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1,
        taskNum--;

        tasks.getTask(taskNum).setDone(stats);

        return ui.getDoneSequence(tasks, taskNum);

    }

}

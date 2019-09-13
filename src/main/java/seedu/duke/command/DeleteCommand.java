package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();

        int taskNum = Parser.getDeletedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1) by 1.
        taskNum--;

        if (taskNum >= tasks.getSize()) {
            // taskNum does not exist.
            throw new DukeException("Task no. " + (taskNum + 1) + " does not exist");
        }

        Task taskToDelete = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);

        stats.incrementTotalTasksDeleted();

        return ui.getDeleteSequence(tasks, taskToDelete);

    }

}

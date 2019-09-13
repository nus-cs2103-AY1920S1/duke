package seedu.duke.command;

import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class ByeCommandCli extends Command {

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws IOException {

        stats.incrementTotalCommandsExecuted();

        // Clear the tasks txt file and adds headers.
        taskStorage.clearTaskFileBeforeSaving();

        // Saves the task list to the file, following the pre-defined format.
        for (int i = 0; i < tasks.getSize(); i++) {
            taskStorage.writeToTaskFile(tasks.getTask(i).toSaveString());
        }

        // Saves the stats data to the stats file.
        statStorage.saveStatFile(stats);

        return ui.getByeSequence();

    }
}

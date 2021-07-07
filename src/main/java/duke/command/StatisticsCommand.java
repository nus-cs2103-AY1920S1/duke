package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class controls the statistics command.
 */
public class StatisticsCommand extends Command {

    public StatisticsCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int numberOfTasksCompleted = 0;
        for (Task task : tasks.getTasks()) {
            if (task.isDone()) {
                numberOfTasksCompleted++;
            }
        }
        return ui.showStatistics(numberOfTasksCompleted);
    }
}
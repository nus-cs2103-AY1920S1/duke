package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.List;

public class ListByPriorityCommand extends Command {

    /**
     * Executes {@link Command} to list all {@link Task} in {@link TaskList}
     * by order of priority. Priorities with the smallest integer
     * value are placed on the top.
     *
     * @param tasks Current {@link TaskList}
     * @param ui {@link Ui} currently in use
     * @param storage Current {@link Storage} in use
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList sortedList = tasks.getSortedList();

        ui.showPriorityListHeader();

        ui.showPriorityList(sortedList);
    }

    /**
     * Checks if {@link Command} is {@link ExitCommand}.
     *
     * @return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

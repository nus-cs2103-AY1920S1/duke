package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String keyString;

    public FindCommand(String keyString) {
        this.keyString = keyString;
    }

    /**
     * Finds a {@link Task} using an input string among descriptions
     * of the tasks in the current {@link TaskList}.
     *
     * @param tasks Current list of tasks
     * @param ui UI being used
     * @param storage Current storage in use
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList relevantList = tasks.getRelevantList(keyString);

        if (relevantList.isEmpty()) {
            ui.showFindFailedMessage();
            return;
        }

        ui.showFindMessage();

        ui.showList(relevantList);
    }

    /**
     * Tests if {@link Command} is {@link ExitCommand}.
     *
     * @return Return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

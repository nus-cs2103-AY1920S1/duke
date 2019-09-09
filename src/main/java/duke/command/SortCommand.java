package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class SortCommand extends Command {

    protected String typeToSort;

    /**
     * Class constructor specifying the keywords to search for from the tasklist.
     *
     * @param typeToSort The keywords to be searched.
     */

    public SortCommand(String typeToSort) {
        super();
        this.typeToSort = typeToSort;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ArrayList<? extends Task> toSort = tasks.findTaskOfType(this.typeToSort);
            for (Task task : toSort) {
                tasks.add(task);
            }
            ui.printTaskList(toSort);
        } catch (AssertionError e) {
            ui.printError(e.getMessage());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }
}

package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Add Command Object.
 */
public class AddCommand extends Command {

    public Task taskToAdd;

    /**
     * Constructor for AddCommand object.
     * @param t is the Task to be added.
     */
    public AddCommand(Task t) {
        super(false);
        this.taskToAdd = t;

    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(taskToAdd);
        storage.writeListToFile(taskList);
        return ui.showAddedTask(taskList);
    }
}

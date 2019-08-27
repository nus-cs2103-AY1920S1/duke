package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.ToDo;
import java.io.IOException;

/**
 * To Do command
 */
public class ToDoCommand extends Command {
    ToDo td;

    /**
     * Initialise with To Do task
     * @param td
     */
    public ToDoCommand(ToDo td) {
        this.td = td;
    }

    /**
     * Adds To Do to Task List and saves it in Storage.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addToDo(td);
            ui.showAddedMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Does not exit
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}
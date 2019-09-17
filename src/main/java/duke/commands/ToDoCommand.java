package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.ToDo;
import duke.ui.UI;

import java.io.IOException;

/**
 * To Do command.
 */
public class ToDoCommand extends Command {
    ToDo td;

    /**
     * Initialise with To Do task.
     *
     * @param td To Do
     */
    public ToDoCommand(ToDo td) {
        this.td = td;
    }

    /**
     * Adds To Do to Task List and saves it in Storage.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     * @return Added To Do message or error message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addTask(td);
            storage.save(tasks.getTasks());
            return ui.getAddedMessage(taskMessage, tasks.getTasksSize());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Does not exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}
package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;
import duke.task.Todo;

/**
 * Represents the command giving a todo task.
 */
public class TodoCommand implements Command {
    private String task;

    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Adds a new ToDo object in the list of tasks and saves this new task list in the hard disk
     *      *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public String execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        if (isDuplicate(task, tasks)) {
            throw new DukeException("OOPS!!! Duplicate task already exists!");
        }
        Todo td = new Todo(task, false);
        tasks.addTask(td);
        storage.appendToFile(td);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                td.toString(), tasks.getTasksSize());
    }

    public boolean isDuplicate(String task, TaskList tasks) {
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            if (tasks.getTask(i).getName().equals(task)) {
                return true;
            }
        }
        return false;
    }
}

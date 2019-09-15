package commands;

import exceptions.DukeException;
import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;


public class DeleteCommand extends Command {
    private int taskId;

    /**
     * Create a delete command.
     *
     * @param taskId the id of the task
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Delete the task from the list of tasks.
     * Give feedback to the user upon successful deletion, and
     * finally write the new task list to file.
     *
     * @param tasks the object that contains the current list of tasks
     * @param ui to give feedback to the user
     * @param storage enables writing to file
     * @return feedback from Duke
     * @throws DukeException if there are any problems writing to file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String str;
        if (taskId > tasks.getSize()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task toDelete = tasks.deleteTask(taskId - 1);
            storage.writeToFile(tasks);
            str = "Noted. I've removed this task:\n" + " " + toDelete.toString() + "\nNow you have " + tasks.getSize()
                    + " tasks in the list.";
            return str;
        }
    }
}

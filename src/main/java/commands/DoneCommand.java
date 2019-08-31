package commands;

import exceptions.DukeException;
import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class DoneCommand extends Command {
    private int taskId;

    /**
     * Create a done command.
     *
     * @param taskId the id of the task
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Mark the right task from the list of tasks as done.
     * Give feedback to the user upon successful marking, and
     * finally write the new task list to file.
     *
     * @param tasks the object that contains the current list of tasks
     * @param ui to give feedback to the user
     * @param storage enables writing to file
     * @throws DukeException if there are any problems writing to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String str;
        if (taskId > tasks.getSize()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task doneTask = tasks.getTask(taskId - 1);
            doneTask.markAsDone();
            str = "Nice! I've marked this task as done:\n" + " " + doneTask.toString();
            ui.addBorder(str);
        }
        storage.writeToFile();
    }
}

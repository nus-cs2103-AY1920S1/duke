package commands;

import exceptions.DukeException;
import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class AddCommand extends Command {
    private Task task;

    /**
     * Create add commands.
     *
     * @param task todo, event or deadline task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Add the task to the list of tasks.
     * Give feedback to the user upon successful addition, and
     * finally write the new task list to file.
     *
     * @param tasks the object that contains the current list of tasks
     * @param ui to give feedback to the user
     * @param storage enables writing to file
     * @throws DukeException if there are any problems writing to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        int taskNum = tasks.getSize();
        String feedback = "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskNum
                + " tasks in the list.";
        ui.addBorder(feedback);
        storage.writeToFile();
    }
}

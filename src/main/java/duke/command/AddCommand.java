package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Storage;
import duke.ui.Ui;

/**
 * AddCommands represent the addition of Tasks to the TaskList.
 */
public class AddCommand extends Command {
    private Task userTask;

    /**
     * Constructor for AddCommand.
     * @param userTask task to be added to TaskList
     */
    public AddCommand(Task userTask) {
        super();
        isExit = false;
        this.userTask = userTask;
    }

    /**
     * Adds a task to the TaskList, displays messages to user, and saves tasks to the hard disk.
     * @param tasks a TaskList to add the Task to
     * @param ui Ui object
     * @param storage Storage object to save changes to text file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(userTask);
        Integer numberOfTasks = tasks.getNumberOfTasks();

        message += ui.showAddMessage();
        message += ui.showTask(userTask);
        message += ui.showNumberOfTasks(numberOfTasks);

        storage.writeTaskToFile(tasks);
        return message;
    }
}
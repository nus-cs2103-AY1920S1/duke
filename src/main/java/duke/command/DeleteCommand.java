package duke.command;

import duke.task.TaskList;
import duke.task.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * DeleteCommands represents user commands to delete a task.
 */
public class DeleteCommand extends Command {
    private Integer taskNumber;

    /**
     * Constructor for DeleteCommand.
     * @param taskNumber index of task to delete from TaskList
     */
    public DeleteCommand(Integer taskNumber) {
        super();
        isExit = false;
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the TaskList, displays messages to user, and saves changes to the hard disk.
     * @param tasks the TaskList to delete the task from
     * @param ui Ui object to display messages
     * @param storage Storage object to save changes to
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(taskNumber);
        tasks.delete(taskNumber);
        Integer numberOfTasks = tasks.getNumberOfTasks();

        message += ui.showDeleteMessage();
        message += ui.showTask(taskToDelete);
        message += ui.showNumberOfTasks(numberOfTasks);

        storage.writeTaskToFile(tasks);
        return message;
    }
}

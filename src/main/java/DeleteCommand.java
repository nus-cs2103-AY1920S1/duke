/**
 * DeleteCommands represents user commands to delete a task.
 */
public class DeleteCommand extends Command {
    private Integer taskNumber;
    public DeleteCommand(Integer taskNumber) {
        super();
        isExit = false;
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the TaskList, displays messages to user, and saves changes to the hard disk
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(taskNumber);
        tasks.delete(taskNumber);
        Integer numberOfTasks = tasks.getNumberOfTasks();

        ui.showDeleteMessage();
        ui.showTask(taskToDelete);
        ui.showNumberOfTasks(numberOfTasks);

        storage.writeTaskToFile(tasks);
    }
}

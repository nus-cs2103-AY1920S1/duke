/**
 * DoneCommands represents user commands to mark a task as done.
 */
public class DoneCommand extends Command {
    private Integer taskNumber;

    /**
     * Constructor for DoneCommand.
     * @param taskNumber index of task to mark as done
     */
    public DoneCommand(Integer taskNumber) {
        super();
        this.taskNumber = taskNumber;
        isExit = false;
    }

    /**
     * Mark a task as done, displays the marked task, then saves the changes.
     * @param tasks a TaskList that stores the list of tasks
     * @param ui Ui object to display messages
     * @param storage Storage object to save changes to
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskCompleted = tasks.getTask(taskNumber);
        taskCompleted.markAsDone();

        ui.showDoneMessage();
        ui.showTask(taskCompleted);
        storage.writeTaskToFile(tasks);
    }
}

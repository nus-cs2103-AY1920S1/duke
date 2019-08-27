/**
 * DoneCommands represents user commands to mark a task as done
 */
public class DoneCommand extends Command {
    private Integer taskNumber;

    public DoneCommand(Integer taskNumber) {
        super();
        this.taskNumber = taskNumber;
        isExit = false;
    }

    /**
     * Mark a task as done, displays the marked task, then saves the changes
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskCompleted = tasks.getTask(taskNumber);
        taskCompleted.markAsDone();

        ui.showDoneMessage();
        ui.showTask(taskCompleted);
        storage.writeTaskToFile(tasks);
    }
}

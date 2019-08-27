public class DoneCommand extends Command {
    private Integer taskNumber;

    public DoneCommand(Integer taskNumber) {
        super();
        this.taskNumber = taskNumber;
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskCompleted = tasks.getTask(taskNumber);
        taskCompleted.markAsDone();

        ui.showDoneMessage();
        ui.showTask(taskCompleted);
        storage.writeTaskToFile(tasks);
    }
}

public class DeleteCommand extends Command {
    private Integer taskNumber;

    public DeleteCommand(Integer taskNumber) {
        super();
        isExit = false;
        this.taskNumber = taskNumber;
    }

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

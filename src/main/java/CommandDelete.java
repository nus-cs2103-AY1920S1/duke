public class CommandDelete extends Command {
    private int taskNumber;

    public CommandDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToDelete;
        try {
            taskToDelete = tasks.remove(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }

        storage.save(tasks);

        ui.printTaskDeleted(tasks, taskToDelete);
    }
}

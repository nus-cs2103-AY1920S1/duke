public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute() throws DukeException {
        Task task = this.taskList.delete(taskId);
        this.ui.displaySingleLine("Noted. I've removed this task:");
        this.ui.displayMessage(task.toString(), 2);
        this.ui.displaySingleLine("\nNow you have "
                + this.taskList.getSize()
                + " task(s) in the list.");
        this.storage.saveToDisk(this.taskList);
    }
}

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute() throws DukeException {
        Task task = this.taskList.delete(taskId);
        task.markAsDone();
        this.ui.displaySingleLine("Nice! I've marked this task as done:");
        this.ui.displayMessage(task.toString(), 2);
    }
}

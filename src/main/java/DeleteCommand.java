public class DeleteCommand extends Command {
    int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    public void execute(TaskList tasks, UI ui) {
        String taskMessage = tasks.delete(i);
        ui.showDeleteMessage(taskMessage, tasks.getTasksSize());
    }

    public boolean isExit() {
        return false;
    }

}
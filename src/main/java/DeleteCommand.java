public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int num) {
        this.taskNum = num;
    }

    public void execute(TaskList tasks) throws DukeException {
        tasks.deleteTask(taskNum);
    }
}

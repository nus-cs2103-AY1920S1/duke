public class DoneCommand extends Command {
    private int taskNum;
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    public void execute(TaskList tasks) throws DukeException {
        tasks.markAsDone(taskNum);
    }
}

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.doTask(this.taskNumber);
    }

    public boolean isExit() {
        return false;
    }
}

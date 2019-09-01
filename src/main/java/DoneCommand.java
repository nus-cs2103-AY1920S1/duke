import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.list.get(taskNo).markAsDone();
        ui.showDoneMessage(taskList.list.get(taskNo));
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}

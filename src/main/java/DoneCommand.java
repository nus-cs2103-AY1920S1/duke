import java.util.List;

public class DoneCommand extends Command {

    private int target;

    public DoneCommand(int target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task doneTask = taskList.getTaskAt(target);
        doneTask.markDone();
        ui.showTaskDone(doneTask);
    }
}

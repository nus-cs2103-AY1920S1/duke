import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ui.showTaskList(taskList);
    }
}

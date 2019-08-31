import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        ArrayList<Task> allTask = tasks.getList();
        for (int i = 0; i < allTask.size(); i++) {
            ui.showMessage(6, (i + 1) + "." + allTask.get(i).toString());
        }
    }
}

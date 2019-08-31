import java.util.ArrayList;

public class listCommand implements Command{
    boolean isExist = false;

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
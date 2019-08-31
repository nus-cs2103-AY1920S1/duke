import java.util.ArrayList;

public class exitCommand implements Command{
    boolean isExist = true;

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        ui.showLeaving();
        storage.saveFile(list);
    }
}

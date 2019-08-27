import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        ArrayList<Task> list = tasks.getTasks();
        ui.list(list);
    }
}
import java.io.IOException;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        Task task = taskList.done(this.index);

        ui.done(task);

        storage.save(taskList.list);
    }
}

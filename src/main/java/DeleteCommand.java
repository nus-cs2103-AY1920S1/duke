import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        Task task = taskList.delete(index);

        ui.delete(taskList.list, task);

        storage.save(taskList.list);
    }
}

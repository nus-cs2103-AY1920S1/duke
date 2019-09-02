import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand (Task task) {
        this.task = task;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        taskList.add(task);

        ui.add(taskList.list);

        storage.save(taskList.list);

    }
}

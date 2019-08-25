import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        ui.printRemoveMsg();
        ui.printLatest(list);
        list.deleteTask(index);
        ui.printNumTask(list);
        storage.writeToFile(list);
    }
}

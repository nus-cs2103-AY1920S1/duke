import java.io.IOException;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.getTask(index).markAsDone();
        list.getTask(index).updateDone();
        storage.writeToFile(list);
    }

}

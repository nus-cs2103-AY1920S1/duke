import java.text.ParseException;

public class DoneCommand extends Command {

    private int indexToDone;

    public DoneCommand(int indexToDone) {
        this.indexToDone = indexToDone - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {
        super.execute(tasks, ui, storage);
        Task task = tasks.getTasks().get(indexToDone);
        task.markAsDone();
        ui.done(task);
    }
}

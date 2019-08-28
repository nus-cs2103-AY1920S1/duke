import java.text.ParseException;

public class DeleteCommand extends Command {

    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete =  indexToDelete - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {
        super.execute(tasks, ui, storage);
        Task task = tasks.getTasks().get(indexToDelete);
        tasks.deleteTask(indexToDelete);
        ui.removeTask(task, tasks.getNumber());
    }
}

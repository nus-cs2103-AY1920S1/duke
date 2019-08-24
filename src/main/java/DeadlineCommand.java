import java.io.IOException;
import java.util.Date;

public class DeadlineCommand extends Command {

    private final String description;
    private final Date by;

    DeadlineCommand(String description, Date by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(this.description, this.by);
        tasks.add(task);
        ui.showTaskAdded(task, tasks);
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.showSaveError();
        }
        return false;
    }

}

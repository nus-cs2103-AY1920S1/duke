import java.io.IOException;
import java.util.Date;

public class EventCommand extends Command {

    private final String description;
    private final Date at;

    EventCommand(String description, Date at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(this.description, this.at);
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

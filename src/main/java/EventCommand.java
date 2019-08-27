import java.io.IOException;

public class EventCommand extends Command {
    Event e;

    public EventCommand(Event e) {
        this.e = e;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addEvent(e);
            ui.showAddedMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}
import java.util.Date;

public class EventCommand extends Command {
    Event e;

    public EventCommand(Event e) {
        this.e = e;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        String taskMessage = tasks.addEvent(e);
        ui.showAddedMessage(taskMessage, tasks.getTasksSize());
    }

    public boolean isExit() {
        return false;
    }

}
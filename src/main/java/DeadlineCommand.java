import java.util.Date;

public class DeadlineCommand extends Command {
    Deadline dl;

    public DeadlineCommand(Deadline dl) {
        this.dl = dl;
    }

    public void execute(TaskList tasks, UI ui) {
        String taskMessage = tasks.addDeadline(dl);
        ui.showAddedMessage(taskMessage, tasks.getTasksSize());
    }

    public boolean isExit() {
        return false;
    }

}
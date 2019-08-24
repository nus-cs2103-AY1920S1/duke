import task.Deadline;
import task.Task;

public class AddDeadlineCommand extends AddCommand {
    private final String time;

    public AddDeadlineCommand(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(this.description, this.time);
        executeAddTask(tasks, ui, task);
    }
}

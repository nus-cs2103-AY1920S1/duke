import java.util.List;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        int taskNum = tasks.size();
        String feedback = "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + taskNum
                + " tasks in the list.";
        ui.addBorder(feedback);
        storage.writeToFile();
    }
}

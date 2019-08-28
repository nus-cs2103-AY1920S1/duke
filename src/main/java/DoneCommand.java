import java.util.List;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) throws DukeException {
        String str;
        if (taskId > tasks.size()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task doneTask = tasks.get(taskId - 1);
            doneTask.markAsDone();
            str = "Nice! I've marked this task as done:\n" + " " + doneTask.toString();
            ui.addBorder(str);
        }
        storage.writeToFile();
    }
}

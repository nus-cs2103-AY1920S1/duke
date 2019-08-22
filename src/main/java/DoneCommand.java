import java.util.List;

class DoneCommand implements Command {
    private final int taskIndex;

    public DoneCommand(String command) {
        taskIndex = Integer.parseInt(command);
    }

    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        Task t = tasks.get(taskIndex - 1);
        t.markAsDone();

        storage.save(tasks);
        ui.displaySuccessfullyDoneTask("Nice! I've marked this task as done:", t);
    }
}

import java.io.IOException;

public class AddTaskCommand extends Command {
    private Task newTask;

    public AddTaskCommand(Task task) {
        newTask = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        tasks.addTask(newTask);
        storage.update(tasks);
        ui.print("Got it. I've added this task:");
        ui.print(newTask.toString());
        ui.print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }
}

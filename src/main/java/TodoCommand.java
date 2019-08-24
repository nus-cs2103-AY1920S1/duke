import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    TodoCommand(String description) {
        this.description = description;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new TodoTask(description);
        tasks.add(t);
        String result = "Got it. I've added this task:\n";
        result += "  " + t + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        ui.dukePrint(result);
        storage.writeToFile(tasks);
    }

    boolean isExit() {
        return false;
    }
}

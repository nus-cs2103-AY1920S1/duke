import java.io.IOException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidDateException, IOException {
        Task t = new DeadlineTask(description, by);
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

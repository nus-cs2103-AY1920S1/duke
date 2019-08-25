import java.io.IOException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Creates a DeadlineCommand with a given description and deadline.
     * @param description Task description
     * @param by Task deadline date in format of "dd/mm/yyyy hhmm" e.g. "31/12/2019 2359"
     */
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

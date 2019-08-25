import java.io.IOException;

public class EventCommand extends Command {
    private String description;
    private String at;

    /**
     * Creates an EventCommand with a given description and event date.
     * @param description Task description
     * @param at Event date in format of "dd/mm/yyyy hhmm" e.g. "31/12/2019 2359"
     */
    EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidDateException, IOException {
        Task t = new EventTask(description, at);
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

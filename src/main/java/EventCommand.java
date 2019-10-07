public class EventCommand extends UndoableCommand {
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

    @Override
    String execute(TaskList tasks, Storage storage) throws DukeInvalidDateException, DukeIoException {
        Task t = new EventTask(description, at);
        tasks.add(t);
        String result = "Got it. I've added this task:\n";
        result += "  " + t + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeToFile(tasks);
        return result;
    }

    @Override
    String undo(TaskList tasks, Storage storage) throws DukeInvalidTaskException, DukeIoException {
        return (new DeleteCommand(tasks.size())).execute(tasks, storage);
    }

    @Override
    boolean isExit() {
        return false;
    }
}

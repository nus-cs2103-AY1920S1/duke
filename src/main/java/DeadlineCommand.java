public class DeadlineCommand extends UndoableCommand {
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

    @Override
    String execute(TaskList tasks, Storage storage) throws DukeInvalidDateException, DukeIoException {
        Task t = new DeadlineTask(description, by);
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

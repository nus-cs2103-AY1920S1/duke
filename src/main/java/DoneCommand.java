/**
 * Represents a Done Command.
 */
public class DoneCommand extends Command {
    /**
     * Constructs a Done Command.
     */
    public DoneCommand(String input) {
        super.input = input;
    }

    /**
     * Determines whether or should the Duke App should terminate.
     *
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the completion of a particular task defines by the last user input.
     *
     * @param tasks   The TaskList of the current Duke App.
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String doneNumber = input.substring(5).trim();
        try {
            int taskNumber = Integer.parseInt(doneNumber) - 1;
            tasks.complete(taskNumber);
            storage.writeList(tasks);
            String output = "Nice! I've marked this task as done:\n";
            output += "    " + tasks.get(taskNumber).toString() + "\n";
            return output;
        } catch (IndexOutOfBoundsException | NumberFormatException err) {
            throw new InvalidDescriptionDukeException("done", doneNumber);
        }
    }
}

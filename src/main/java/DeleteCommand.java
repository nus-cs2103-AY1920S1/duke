/**
 * Represents a Delete Command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a Delete Comamnd object.
     */
    public DeleteCommand(String input) {
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
     * Executes the  deletion of a particular task based on the latest input.
     *
     * @param tasks   The TaskList of the current Duke App.
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String deleteNumber = input.substring(6).trim();
        assert deleteNumber != null || !deleteNumber.equals("") : "deleteNumber should not be null";
        if (deleteNumber.length() == 0) {
            return "No number detected to be removed";
        }
        try {
            int taskNumber = Integer.parseInt(deleteNumber) - 1;
            String output = "Noted. I've removed this task:\n";
            output += "    " + tasks.get(taskNumber).toString() + "\n";
            tasks.remove(taskNumber);
            storage.writeList(tasks);
            output += "Now you have " + tasks.size() + " tasks in the list.\n";
            return output;
        } catch (IndexOutOfBoundsException | NumberFormatException err) {
            throw new InvalidDescriptionDukeException("delete", deleteNumber);
        }
    }
}

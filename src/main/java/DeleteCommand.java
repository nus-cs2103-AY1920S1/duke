/**
 * Represents a Delete Command.
 */
public class DeleteCommand extends Command{
    /**
     * Constructs a Delete Comamnd object.
     */
    public DeleteCommand() {
    }

    /**
     * Determines whether or should the Duke App should terminate.
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the  deletion of a particular task based on the latest input.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String deleteNumber = input.substring(7).split(" ")[0].trim();
        try {
            int taskNumber = Integer.parseInt(deleteNumber) - 1;
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(6, tasks.get(taskNumber).toString());
            tasks.remove(taskNumber);
            storage.writeList(tasks);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException err) {
            throw new InvalidDescriptionDukeException("delete", deleteNumber);
        }
    }
}

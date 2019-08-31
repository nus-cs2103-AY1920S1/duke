/**
 * Represents a Done Command.
 */
public class DoneCommand extends Command{
    /**
     * Constructs a Done Command.
     */
    public DoneCommand() {

    }

    /**
     * Determines whether or should the Duke App should terminate.
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the completion of a particular task defines by the last user input.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String doneNumber = input.substring(5);
        try {
            int taskNumber = Integer.parseInt(doneNumber) - 1;
            tasks.complete(taskNumber);
            storage.writeList(tasks);
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(6, tasks.get(taskNumber).toString());
        } catch (IndexOutOfBoundsException | NumberFormatException err) {
            throw new InvalidDescriptionDukeException("done", doneNumber);
        }
    }
}

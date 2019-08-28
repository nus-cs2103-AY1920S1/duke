/**
 * Encapsulates a user command to add a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {

    String inputDeadline = "";

    public void setInputDeadline(String inputDeadline) {
        this.inputDeadline = inputDeadline;
    }

    /**
     * Overridden method. Executes the add deadline command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     * @throws DukeException exception specific to Duke application
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputDeadline.trim().length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputDeadline);
            int firstIndex = slashLocation - 1;
            int secondIndex = slashLocation + 4;
            String deadlineDescription = inputDeadline.substring(9, firstIndex);
            String deadlineBy = inputDeadline.substring(secondIndex);
            Deadline d = new Deadline(deadlineDescription, deadlineBy);
            tasks.addTaskAfterValidation(deadlineBy, d);
            storage.updateChanges(tasks.getDukeTaskList());
        }
    }

    /**
     * Returns the index of the slash symbol in an input string.
     * @param inputString input string
     * @return index of slash symbol
     */
    public int slashLocator(String inputString) {
        return inputString.indexOf("/");
    }
}

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputDeadline.trim().length() == 8) { // the length of the string "deadline" is 8
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputDeadline);
            int indexOfWhiteSpace = slashLocation - 1; // index of whitespace just before the slash
            int indexOfFirstChar = slashLocation + 4; // index of first character of deadlineBy date time
            String deadlineDescription = inputDeadline.substring(9, indexOfWhiteSpace);
            String deadlineBy = inputDeadline.substring(indexOfFirstChar);
            Deadline d = new Deadline(deadlineDescription, deadlineBy);
            tasks.addTaskAfterValidation(deadlineBy, d);
            storage.updateChanges(tasks.getDukeTaskList());
        }
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputDeadline.trim().length() == 8) { // the length of the string "deadline" is 8
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputDeadline);
            int indexOfWhiteSpace = slashLocation - 1; // index of whitespace just before the slash
            int indexOfFirstChar = slashLocation + 4; // index of first character of deadlineBy date time
            String deadlineDescription = inputDeadline.substring(9, indexOfWhiteSpace);
            String deadlineBy = inputDeadline.substring(indexOfFirstChar);
            Deadline d = new Deadline(deadlineDescription, deadlineBy);
            String output = tasks.addTaskAfterValidationForGui(deadlineBy, d);
            storage.updateChanges(tasks.getDukeTaskList());
            return output;
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

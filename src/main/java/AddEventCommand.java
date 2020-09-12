/**
 * Encapsulates a user command to add an event to the task list.
 */
public class AddEventCommand extends Command {

    String inputEvent = "";

    public void setInputEvent(String inputEvent) {
        this.inputEvent = inputEvent;
    }

    /**
     * Overridden method. Executes the add event command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     * @throws DukeException exception specific to Duke application
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputEvent.trim().length() == 5) { // length of the string "event" is 5
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputEvent);
            int indexOfWhiteSpace = slashLocation - 1; // index of whitespace just before the slash
            int indexOfFirstChar = slashLocation + 4; // index of first character of eventAt date time
            String eventDescription = inputEvent.substring(6, indexOfWhiteSpace);
            String eventAt = inputEvent.substring(indexOfFirstChar);
            Event e = new Event(eventDescription, eventAt);
            tasks.addTaskAfterValidation(eventAt, e);
            storage.updateChanges(tasks.getDukeTaskList());
        }
    }

    @Override
    public String executeForGui (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputEvent.trim().length() == 5) { // length of the string "event" is 5
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputEvent);
            int indexOfWhiteSpace = slashLocation - 1; // index of whitespace just before the slash
            int indexOfFirstChar = slashLocation + 4; // index of first character of eventAt date time string
            String eventDescription = inputEvent.substring(6, indexOfWhiteSpace);
            String eventAt = inputEvent.substring(indexOfFirstChar);
            Event e = new Event(eventDescription, eventAt);
            String output = tasks.addTaskAfterValidationForGui(eventAt, e);
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

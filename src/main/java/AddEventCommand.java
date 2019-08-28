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
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputEvent.trim().length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputEvent);
            int firstIndex = slashLocation - 1;
            int secondIndex = slashLocation + 4;
            String eventDescription = inputEvent.substring(6, firstIndex);
            String eventAt = inputEvent.substring(secondIndex);
            Event e = new Event(eventDescription, eventAt);
            tasks.addTaskAfterValidation(eventAt, e);
            storage.updateChanges(tasks.getDukeTaskList());
        }
    }

    public int slashLocator(String inputString) {
        return inputString.indexOf("/");
    }
}

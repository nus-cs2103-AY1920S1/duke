package command;

import run.Storage;
import run.TaskList;
import run.Ui;
import task.Event;

/**
 * Extends AddCommand class and is used to create and manage a new event task
 */
public class EventCommand extends AddCommand {
    protected String rawString;

    /**
     * Constructor for a new EventCommand that takes in the entire raw string the user
     * enters to be later parsed and separated into the the new event's relevant fields
     * @param rawString complete unparsed user input of event creation request
     */
    public EventCommand(String rawString) {
        this.rawString = rawString;
    }

    /**
     * Adds a new event task to current TaskList, adds this
     * new event into storage and interacts/updates the user through the ui
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        String remove_command = rawString.replaceFirst("event ", "");
        String[] splited = remove_command.split(" /at ");
        Event curr_task = new Event(splited[0], splited[1]);
        tasks.add(curr_task);
        super.addCommandUpdateState();
    }
}
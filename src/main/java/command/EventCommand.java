package command;

import exception.UpdateStateException;
import run.Storage;
import run.TaskList;
import run.Ui;
import task.Event;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * Extends AddCommand class and is used to create and manage a new event task.
 */
public class EventCommand extends AddCommand {
    protected String rawString;

    /**
     * Constructor for a new EventCommand that takes in the entire raw string the user
     * enters to be later parsed and separated into the the new event's relevant fields.
     * @param rawString complete unparsed user input of event creation request
     */
    public EventCommand(String rawString) {
        this.rawString = rawString;
    }

    /**
     * Adds a new event task to current TaskList, adds this
     * new event into storage and interacts/updates the user through the ui.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, UpdateStateException, NumberFormatException, DateTimeException {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        String removeCommand = rawString.replaceFirst("event ", "");
        String[] splited = removeCommand.split(" /at ");
        Event currTask = new Event(splited[0], splited[1]);
        String output = tasks.add(currTask);
        super.addCommandUpdateState();
        return output;
    }
}
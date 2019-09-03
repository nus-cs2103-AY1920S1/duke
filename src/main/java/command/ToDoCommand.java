package command;

import exception.DukeException;
import exception.EmptyToDoDescriptionException;
import run.Storage;
import run.TaskList;
import run.Ui;
import task.ToDo;

/**
 * Extends AddCommand class and is used to create and manage a new todo task
 */
public class ToDoCommand extends AddCommand {
    protected String rawString;

    /**
     * Constructor for a new ToDoCommand that takes in the entire raw string the user
     * enters to be later parsed and separated into the the new todo's relevant fields
     * @param rawString complete unparsed user input of todo creation request
     */
    public ToDoCommand(String rawString) throws DukeException {
        if(rawString.equals("todo") || rawString.equals("todo ")) {
            throw new EmptyToDoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.rawString = rawString;
    }

    /**
     * Adds a new todo task to current TaskList, adds this
     * new todo into storage and interacts/updates the user through the ui
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        ToDo curr_task = new ToDo(rawString.replaceFirst("todo ", ""));
        tasks.add(curr_task);
        super.addCommandUpdateState();
    }
}
package command;

import exception.DukeException;
import exception.EmptyToDoDescriptionException;
import exception.UpdateStateException;
import run.Storage;
import run.TaskList;
import run.Ui;
import task.ToDo;

import java.io.IOException;

/**
 * Extends AddCommand class and is used to create and manage a new todo task.
 */
public class ToDoCommand extends AddCommand {
    protected String rawString;

    /**
     * Constructor for a new ToDoCommand that takes in the entire raw string the user
     * enters to be later parsed and separated into the the new todo's relevant fields.
     * @param rawString complete unparsed user input of todo creation request
     */
    public ToDoCommand(String rawString) throws DukeException {
        if (rawString.equals("todo") || rawString.equals("todo ")) {
            throw new EmptyToDoDescriptionException(DukeException.EMPTY_TODO_DESCRIPTION_MESSAGE);
        }
        this.rawString = rawString;
    }

    /**
     * Adds a new todo task to current TaskList, adds this
     * new todo into storage and interacts/updates the user through the ui.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, UpdateStateException {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        ToDo currTask = new ToDo(rawString.replaceFirst("todo ", ""));
        String output = tasks.add(currTask);
        super.addCommandUpdateState();
        return output;
    }
}
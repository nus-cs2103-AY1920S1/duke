package duke.command;

import java.util.ArrayList;

import duke.task.Task;


/**
 * duke.command.TaskList class. Manages all input, output and display related commands affecting the task list.
 */
public class TaskList {

    /** Stores the ArrayList of tasks. */
    private ArrayList<Task> taskList;
    /** Stores the storage object used to read/write to file. */
    private Storage storage;

    /**
     * Creates a new duke.command.TaskList object.
     * @param storage The storage object previously created to read/write from file.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        taskList = storage.loadFromFile();
    }

    /**
     * Lists all tasks.
     * @return A string containing the list of tasks.
     * @throws DukeException If no tasks are available.
     */
    public String list() throws DukeException {
        return List.list(taskList);
    }

    /**
     * Marks a task as done.
     * @param index The index number of the task to be marked done (starting from 1).
     * @return A string confirming message that the task was successfully deleted.
     * @throws DukeException If the task at index does not currently exist.
     */
    public String markAsDone(int index) throws DukeException {
        return MarkAsDone.markAsDone(taskList, index, storage);
    }

    /**
     * Deletes a task.
     * @param index The index number of the task to be marked done (starting from 1).
     * @return A string confirming message that the task was successfully deleted.
     * @throws DukeException If the task at index does not currently exist.
     */
    public String delete(int index) throws DukeException {
        return Delete.delete(taskList, index, storage);
    }

    /**
     * Creates a new ToDo task.
     * @param params The parameters (description) for the ToDo task.
     * @return A string confirming that the task was successfully added.
     * @throws DukeException If the description is empty.
     */
    public String createToDo(String[] params) throws DukeException {
        return CreateToDo.createToDo(taskList, params, storage);
    }

    /**
     * Creates a new Deadline task.
     * @param params The parameters (description, due by date) for the Deadline task.
     * @return A string confirming that the task was successfully added.
     * @throws DukeException If the description or due by date is empty or in the wrong format.
     */
    public String createDeadline(String[] params) throws DukeException {
        return CreateDeadline.createDeadline(taskList, params, storage);
    }

    /**
     * Creates a new Event task.
     * @param params The parameters (description, due by date) for the Event task.
     * @return A string confirming that the task was successfully added.
     * @throws DukeException If the description or due by date is empty or in the wrong format.
     */
    public String createEvent(String[] params) throws DukeException {
        return CreateEvent.createEvent(taskList, params, storage);
    }

    /**
     * Creates a new note.
     * @param params The parameters (content) for the note.
     * @return A string confirming that the task was successfully added.
     * @throws DukeException if the note's contents are empty.
     */
    public String createNote(String[] params) throws DukeException {
        return CreateNote.createNote(taskList, params, storage);
    }

    /**
     * Finds all the tasks matching a certain keyword.
     * @param params A string array of the keyword(s) to search for
     * @return A string representing all the tasks matching the keyword
     * @throws DukeException If no search keyword is provided.
     */
    public String findEvent(String[] params) throws DukeException {
        return FindEvent.findEvent(taskList, params);
    }

    /**
     * Allows to user to check what commands are available.
     * @return A string representing the manual for duke.main.Duke.
     */
    public String help() {
        return Help.help();
    }

}

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * TaskList class. Manages all input, output and display related commands affecting the task list.
 */
public class TaskList {

    /** Header line for list operation. */
    private static String tasklist_header = "\t Here are the tasks in your list:\n";
    /** Done message. */
    private static String done_message = "\t Nice! I've marked this task as done:\n";
    /** Task added successfully message. */
    private static String task_added_message = "\t Got it. I've added this task:\n";
    /** Task deleted successfully message. */
    private static String delete_message = "\t Noted. I've removed this task:\n";
    private static String matching_message = "\tHere are the matching tasks in your list:\n";

    /** Stores the ArrayList of tasks. */
    private ArrayList<Task> taskList;
    /** Stores the storage object used to read/write to file. */
    private Storage storage;
    /** Stores the DateTimeFormatter object used to specify the format of date/time objects when printed. */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a new TaskList object.
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
        if (taskList.isEmpty()) {
            throw new DukeException("There are no tasks to display.");
        }
        StringBuilder s = new StringBuilder(tasklist_header);
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\t ").append(i + 1).append(".").append(taskList.get(i));
        }

        return s.toString();
    }

    /**
     * Marks a task as done.
     * @param index The index number of the task to be marked done (starting from 1).
     * @return A string with the confirmation message that the task was successfully deleted.
     * @throws DukeException If the task at index does not currently exist.
     */
    public String markAsDone(int index) throws DukeException {
        Task current;
        try {
            current = taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task with that ID.");
        }
        boolean status = current.markAsComplete();
        if (!status) {
            throw new DukeException("Action already marked as done!");
        }
        String s = done_message + "\t   " + current;

        boolean isSaved = save();
        assert isSaved == true : "Error: not saved to disk.";

        return s;
    }

    /**
     * Deletes a task.
     * @param index The index number of the task to be marked done (starting from 1).
     * @return A string with the confirmation message that the task was successfully deleted.
     * @throws DukeException If the task at index does not currently exist.
     */
    public String delete(int index) throws DukeException {
        Task current;
        try {
            current = taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task with that ID.");
        }
        String s = delete_message + "\t   " + current;

        boolean isSaved = save();
        assert isSaved == true : "Error: not saved to disk.";

        return s;
    }

    /**
     * Creates a new ToDo task.
     * @param params The parameters (description) for the ToDo task.
     * @return A string with the confirmation that the task was successfully added.
     * @throws DukeException If the description is empty.
     */
    public String createToDo(String[] params) throws DukeException {
        String task = Parser.joinStrings(params);
        if (task.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task current = new ToDo(task);
        taskList.add(current);
        String s = task_added_message + "\t   " + current + totalNoOfTasks();

        boolean isSaved = save();
        assert isSaved == true : "Error: not saved to disk.";

        return s;
    }

    /**
     * Creates a new Deadline task.
     * @param params The parameters (description, due by date) for the Deadline task.
     * @return A string with the confirmation that the task was successfully added.
     * @throws DukeException If the description or due by date is empty or in the wrong format.
     */
    public String createDeadline(String[] params) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("The description and due date of a deadline cannot be empty.");
        }
        String[] details = Parser.splitByIdentifier(params, "/by");
        if (details[0].isEmpty() && details[1].isEmpty()) {
            throw new DukeException("You need to specify a due date, denoted by /by");
        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        Task current;
        try {
            current = new Deadline(details[0], LocalDateTime.parse(details[1], formatter));
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to specify a due date in the format dd/MM/yyyy HHmm");
        }
        taskList.add(current);
        String s = task_added_message + "\t   " + current + totalNoOfTasks();

        boolean isSaved = save();
        assert isSaved == true : "Error: not saved to disk.";

        return s;
    }

    /**
     * Creates a new Event task.
     * @param params The parameters (description, due by date) for the Event task.
     * @return A string with the confirmation that the task was successfully added.
     * @throws DukeException If the description or due by date is empty or in the wrong format.
     */
    public String createEvent(String[] params) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("The description and due date of an event cannot be empty.");
        }
        String[] details = Parser.splitByIdentifier(params, "/at");
        if (details[0].isEmpty() && details[1].isEmpty()) {
            throw new DukeException("You need to specify a due date, denoted by /at");
        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        Task current;
        try {
            current = new Event(details[0], LocalDateTime.parse(details[1], formatter));
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to specify a due date in the format dd/MM/yyyy HHmm");
        }
        taskList.add(current);
        String s = task_added_message + "\t   " + current + totalNoOfTasks();

        boolean isSaved = save();
        assert isSaved == true : "Error: not saved to disk.";

        return s;
    }

    /**
     * Finds all the tasks matching a certain keyword.
     * @param params A string array of the keyword(s) to search for
     * @return A string representing all the tasks matching the keyword
     * @throws DukeException If no search keyword is provided.
     */
    public String findEvent(String[] params) throws DukeException {
        String searchKey = Parser.joinStrings(params);
        if (searchKey.isEmpty()) {
            throw new DukeException("You must specify a keyword to search by.");
        }
        StringBuilder s = new StringBuilder(matching_message);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(searchKey)) {
                s.append("\t ").append(i + 1).append(".").append(taskList.get(i));
            }
        }
        return s.toString();
    }

    /**
     * Prints a string with the total number of tasks currently stored.
     * @return A string describing the total number tasks currently stored.
     */
    private String totalNoOfTasks() {
        int noOfTasks = taskList.size();
        return "\t Now you have " + (noOfTasks) + (noOfTasks == 1 ? " task" : " tasks") + " in the list.\n";
    }

    /**
     * Tells the Storage object to save the tasks to file.
     */
    private boolean save() {
        try {
            storage.saveToFile(taskList);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing task to disk. Your changes were not saved. Check your file permissions?");
            return false;
        }
    }

}

package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;
import duke.exception.DukeException;

import java.util.Arrays;
import java.util.Date;

/**
 * Represents an add command when the user typed todo, deadline or event.
 * It has an execution method to add which ever task needed for it.
 */

public class AddCommand extends Command {
    private String[] parsedString;
    private Date date;

    /**
     * Returns the string array parsedString.
     *
     * @return Parsed string.
     */
    public String[] getParsedString() {
        return parsedString;
    }

    /**
     * Returns the date of the task.
     * If task does not have date return null.
     *
     * @return Date of the task
     */
    public Date getDate() {
        if (date != null) {
            return date;
        } else {
            return null;
        }
    }

    /**
     * Initializes an AddCommand instance with date and command.
     * Since it must have a date it will be either a deadline or event task.
     *
     * @param command Include type of task and content of the task
     * @param date A Date object which specifies the date for the task
     */
    public AddCommand(String[] command, Date date) {
        assert command.length > 0;
        assert date != null;
        this.parsedString = command;
        this.date = date;
    }

    /**
     * Initializes an AddCommand instance with and command only.
     * Since it does not have a date it will be a todo task.
     *
     * @param command Include type of task and content of the task.
     */
    public AddCommand(String[] command) {
        assert command.length > 0;
        this.parsedString = command;
    }

    /**
     * Creates a Task object from the command and date information.
     * Add the newly created object to task list and update the storage file.
     *
     * @param tasks The list of task currently recorded.
     * @param ui An UI object.
     * @param storage Storage of the current list of tasks.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        Task task;
        switch (parsedString[0]) {
        case "todo":
            task = new ToDo(parsedString[1]);
            break;
        case "deadline":
            task = new Deadline(parsedString[1], date);
            break;
        case "event":
            task = new Event(parsedString[1], date);
            break;
        default:
            // if the user type anything besides the three types of item
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        storage.updateFile(tasks);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task\n");
        sb.append("  " + task.toString() + "\n");
        if (tasks.size() == 1) {
            sb.append("Now you have 1 task in the list\n");
        } else {
            sb.append(String.format("Now you have %d tasks in the list\n", tasks.size()));
        }
        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Checks if this is the exit command.
     *
     * @return false as it is an AddCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Get the command string
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Compare another object with this object to see if they have the same
     * information.
     *
     * @param o The other object that is going to be compared to this.
     * @return true or false based on the execution of the method.
     */
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof AddCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        AddCommand c = (AddCommand) o;

        // Compare the data members and return accordingly
        if (date != null) {
            return Arrays.equals(this.parsedString, c.parsedString)
                    && date.equals(c.date);
        } else {
            return Arrays.equals(this.parsedString, c.parsedString);
        }
    }
}

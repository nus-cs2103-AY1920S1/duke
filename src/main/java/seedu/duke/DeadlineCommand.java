package seedu.duke;

import java.io.IOException;
import java.util.Date;

/**
 * Represents a Deadline Command.
 * A <code>DeadlineCommand</code> object corresponds to a command with a description that starts with "deadline" and
 * contains a date and time.
 */
public class DeadlineCommand extends Command {

    private String command;
    private Date dateTime;

    /**
     * Constructor of the DeadlineCommand class.
     *
     * @param command the description of command
     * @param dateTime the date and time of the task
     */
    public DeadlineCommand(String command, Date dateTime) {
        this.command = command;
        this.dateTime = dateTime;
    }

    /**
     * Adds the task to the list of tasks.
     * Prints the adding message, the task added and the number of tasks in the list.
     * Update the datafile with the new task.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     * @throws IOException on input error
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(new Deadline(command, dateTime));
        String output = ui.printAddMsg() + ui.printLatest(list) + ui.printNumTask(list);
        storage.appendToFile(list);
        return output;
    }

}

package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommand extends Command {
    private Task t;
    private String type;
    private String description;
    private String time;

    /**
     * Initialises an AddCommand.
     *
     * @param type        The type of AddCommand
     * @param description The description of the command
     * @throws DukeException In the event of empty descriptions/invalid commands/ invalid command formats.
     */
    public AddCommand(String type, String[] description) throws DukeException {

        try {
            String[] details;
            this.description = description[1].trim();
            this.type = type;
            switch ( type ) {
            case "todo":
                t = new ToDo(parseDescription());
                break;
            case "deadline":
                t = new Deadline(parseDescription("/by"), parseDate());
                break;
            case "event":
                t = new Event(parseDescription("/at"), parseDate());
                break;
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a " + type + " cannot be empty");
        }
    }

    /**
     * Executes the AddCommand, adds the task to the LinkedList and saves it to the file.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param ui      The Ui for printing purposes.
     * @param storage The Storage for saving tasks to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.allTasks.add(t);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n" + t);
        if (tasks.allTasks.size() == 1) {
            sb.append("\nNow you have ")
              .append(tasks.allTasks.size())
              .append(" task in the list.");
        } else {
            sb.append("\nNow you have ")
              .append(tasks.allTasks.size())
              .append(" tasks in the list.");
        }
        storage.appendTaskToFile(t);
        ui.printMessage(sb.toString());
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Parses a string and reformats it as a date.
     *
     * @return The string representation of the new date object.
     */
    public String parseDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(time.replaceAll("-", "/"));
            sdf.applyPattern("EEE, d MMM yyyy, hh:mm a");
            return sdf.format(myDate);
        } catch (ParseException e) {
            return time.trim();
        }
    }

    /**
     * Parses the command description for to-dos and checks if it is empty/null.
     *
     * @return The parsed description only if it is not empty/null.
     */
    public String parseDescription() {
        if (description.length() < 1) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return description;
        }
    }

    /**
     * Parses the command description with delimiters for events and deadlines, and checks if it is in the correct format.
     *
     * @param delimiter The delimiter that splits the description.
     * @return The parsed description.
     * @throws DukeException In the event that the command is in the wrong format.
     */
    public String parseDescription(String delimiter) throws DukeException {
        String[] details = description.split(delimiter);
        if (details.length == 1) {
            if (details[0].length() != 0) {
                throw new DukeException("Incorrect format. \nPlease try again with the format below: \n" +
                        type + " ($task_name) " + delimiter + " ($date/day)");
            }
        }
        this.time = details[1];
        return details[0];
    }
}

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
            switch (type) {
                case "todo":
                    t = new ToDo(parseDescription(description[1].trim()));
                    break;
                case "deadline":
                    details = parseDescription(description[1].trim(), "/by", type);
                    t = new Deadline(details[0], parseDate(details[1]));
                    break;
                case "event":
                    details = parseDescription(description[1].trim(), "/at", type);
                    t = new Event(details[0], parseDate(details[1]));
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
            sb.append("\nNow you have ").append(tasks.allTasks.size()).append(" task in the list.");
        } else {
            sb.append("\nNow you have ").append(tasks.allTasks.size()).append(" tasks in the list.");
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
     * @param s The string to be reformatted.
     * @return The string representation of the new date object.
     */
    private String parseDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(s.replaceAll("-", "/"));
            sdf.applyPattern("EEE, d MMM yyyy, hh:mm a");
            return sdf.format(myDate);
        } catch (ParseException e) {
            return s;
        }
    }

    /**
     * Parses the command description and checks if it is empty/null.
     * @param description The description to be parsed.
     * @return The description only if it is not empty/null.
     */
    private String parseDescription(String description) {
        if (description.length() < 1) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return description;
        }
    }

    /**
     * Parses the command description with delimiters and checks if it is in the correct format.
     * @param description The description to be parsed.
     * @param delimiter The delimiter that splits the description.
     * @param type The type of command for this parsing.
     * @return The array containing the description and date range.
     * @throws DukeException In the event that the command is in the wrong format.
     */
    private String[] parseDescription(String description, String delimiter, String type) throws DukeException {
        String[] details = description.split(delimiter);
        if (details.length == 1) {
            if (details[0].length() != 0) {
                throw new DukeException("Incorrect format. \nPlease try again with the format below: \n" +
                        type + " ($task_name) " + delimiter + " ($date/day)");
            }
        }
        return details;

    }
}

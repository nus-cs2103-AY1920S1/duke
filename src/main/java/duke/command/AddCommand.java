package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommand extends Command {
    private Task task;
    private String type;
    private String description;
    private String start;
    private String end;

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
            switch (type) {
            case "todo":
                task = new ToDo(parseDescription());
                break;
            case "deadline":
                task = new Deadline(parseDescription("/by"), parseDate(start));
                break;
            case "event":
                task = new Event(parseDescription("/at"), parseDate(start), parseDate(end));
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
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        storage.appendTaskToFile(task);
        return ("Got it. I've added this task:\n" + task + tasks.getStatus());
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
    public Date parseDate(String date) throws DukeException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(date.replaceAll("-", "/"));
            sdf.applyPattern("EEE, d MMM yyyy, hh:mm a");
            String dateString = sdf.format(myDate);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new DukeException("Invalid date format supported. \nPlease try again with the format below: \n"
                    + "11/12/1999 1600");
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
     * Parses the command description with delimiters for events and deadlines and checks if it is in
     * the correct format.
     *
     * @param delimiter The delimiter that splits the description.
     * @return The parsed description.
     * @throws DukeException In the event that the command is in the wrong format.
     */
    public String parseDescription(String delimiter) throws DukeException {
        String[] details = description.split(delimiter);
        String exceptionMessage = "\nPlease try again with the format below: \n"
                + type + " ($task_name) " + delimiter + " ($date/day)";
        if (details.length <= 1 && details[0].length() != 0) {
            throw new DukeException("Incorrect format. " + exceptionMessage);

        } else if (details[0].isBlank()) {
            throw new DukeException("No $task_name specified. " + exceptionMessage);
        }

        if (details[1].contains("to")) {
            String[] dates = details[1].split("to");
            this.start = dates[0];
            this.end = dates[1];
            System.out.println(end);
        } else {
            this.start = details[1];
        }
        return details[0];
    }
}

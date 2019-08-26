import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddCommand extends Command {
    private String[] description;
    private Task t;

    public AddCommand(String type, String[] description) throws DukeException {
        this.description = description;

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

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.allTasks.add(t);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n" + t);
        if (tasks.allTasks.size() == 1) {
            sb.append("\nNow you have " + tasks.allTasks.size() + " task in the list.");
        } else {
            sb.append("\nNow you have " + tasks.allTasks.size() + " tasks in the list.");
        }
        storage.appendTaskToFile(t);
        ui.printMessage(sb.toString());

    }

    public boolean isExit() {
        return false;
    }

    private String parseDate(String s) {
        ArrayList<SimpleDateFormat> allDateFormats = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(s.replaceAll("-", "/"));
            sdf.applyPattern("EEE, d MMM yyyy, hh:mm a");
            return sdf.format(myDate);
        } catch (ParseException e) {
            return s;
        }
    }

    private String parseDescription(String description) {
        if (description.length() < 1) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return description;
        }
    }

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AddCommand extends Command {
    private String[] description;
    private Task t;

    public AddCommand(String type, String[] description) throws DukeException {
        this.description = description;
        try {
            switch (type) {
                case "todo":
                    t = new ToDo(description[1]);
                    break;
                case "deadline":
                    String[] details = description[1].trim().split("/by");
                    if (details.length == 1) {
                        if (details[0].length() != 0) {
                            throw new DukeException("Incorrect format. \nPlease follow this format below: \n[deadline (task_name) /by (date/day)]");
                        }
                    }
                    t = new Deadline(details[0], parseDate(details[1]));
                    break;
                case "event":
                    String[] details2 = description[1].trim().split("/at");
                    if (details2.length == 1) {
                        if (details2[0].length() != 0) {
                            throw new DukeException("Incorrect format. \nPlease follow this format below: \n[event (task_name) /at (date/day)]");
                        }
                    }
                    t = new Event(details2[0], parseDate(details2[1]));
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

    private static String parseDate(String s) {
        ArrayList<SimpleDateFormat> allDateFormats = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm", java.util.Locale.ENGLISH);
            Date myDate = sdf.parse(s.replaceAll("-", "/"));
            sdf.applyPattern("EEE, d MMM yyyy, hh:mm a");
            String sMyDate = sdf.format(myDate);
            return sMyDate;
        } catch (ParseException e) {
            return s;
        }
    }
}

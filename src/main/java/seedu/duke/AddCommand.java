package seedu.duke;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Handles adding tasks to the TaskList.
 */
public class AddCommand extends Command {
    protected static String title;
    protected static String details;

    /**
     * Constructor.
     * @param s String containing Task title.
     * @param det String containing Task timing.
     */
    public AddCommand(String s, String det) {
        title = s;
        details = det;
        type = "add";
    }

    /**
     * Executes the AddCommand and adds a corresponding Task to the TaskList.
     *  @param t TaskList.
     * @param u Ui.
     * @param s Storage.
     * @return
     */
    @Override
    public String execute(TaskList t, Ui u, Storage s) {
        Task task = new Task("null");
        if (title.equals("todo")) {
            task = new ToDo(details);
        } else if (title.equals("deadline")) {
            String[] arr = details.split("/by");
            Date date = convertToDate(arr[1].trim());
            task = new Deadline(arr[0].trim(), date);
        } else if (title.equals("event")) {
            String[] arr = details.split("/at");
            Date date = convertToDate(arr[1].trim());
            task = new Event(arr[0].trim(), date);
        }

        t.list.add(task);

        try {
            String text = task.getType() + " . 0 . " + task.getFullDescription() + "\n";
            s.update(true, text, t);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return u.addLine(task.toString(), t.list.size());
    }

    /**
     * Converts String to Date.
     * @param str String containing date information.
     * @return Date of Task.
     */
    public static Date convertToDate(String str) {
        String[] arr = str.split(" ");
        String[] dateArray = arr[0].split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]) - 1;
        int year = Integer.parseInt(dateArray[2]);
        int hour = Integer.parseInt(arr[1].substring(0, 2));
        int minute = Integer.parseInt(arr[1].substring(2));
        Date d = new GregorianCalendar(year, month, day, hour, minute).getTime();
        return d;
    }
}

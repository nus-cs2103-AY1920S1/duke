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
     * @return a String representing the add message
     */
    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        Task task = createNewTask();
        int size = t.list.size();
        t.list.add(task);
        assert t.list.size() == size + 1: "Current list size should be one more";

        try {
            String text = task.getType() + " . 0 . " + task.getFullDescription() + "\n";
            s.update(true, text, t);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } finally {
            return u.addLine(task.toString(), t.list.size());
        }
    }

    /**
     * Creates a new Task according to the user input.
     * @return a new Task.
     */
    public static Task createNewTask() {
        Task t = null;
        if (title.equals("todo")) {
            t = new ToDo(details);
        } else if (title.equals("deadline")) {
            String[] arr = details.split("/by");
            Date date = convertToDate(arr[1].trim());
            t = new Deadline(arr[0].trim(), date);
        } else if (title.equals("event")) {
            String[] arr = details.split("/at");
            Date date = convertToDate(arr[1].trim());
            t = new Event(arr[0].trim(), date);
        }
        return t;
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

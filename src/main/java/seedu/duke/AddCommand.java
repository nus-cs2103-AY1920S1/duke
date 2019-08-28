package seedu.duke;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddCommand extends Command {
    protected static String title;
    protected static String details;

    public AddCommand(String s, String det) {
        title = s;
        details = det;
        type = "add";
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        Task task = new Task("null");
        if (title.equals("todo")) {
            task = new ToDo(details);
            t.list.add(task);
        } else if (title.equals("deadline")) {
            String[] arr = details.split("/by");
            Date date = convertToDate(arr[1].trim());
            task = new Deadline(arr[0].trim(), date);
            t.list.add(task);
        } else if (title.equals("event")) {
            String[] arr = details.split("/at");
            Date date = convertToDate(arr[1].trim());
            task = new Event(arr[0].trim(), date);
            t.list.add(task);
        }

        try {
            String text = task.getType() + " . 0 . " + task.getFullDescription() + "\n";
            s.update(true, text, t);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

        u.addLine(task.toString(), t.list.size());
    }

    public static Date convertToDate(String str) {
        String[] arr = str.split(" ");
        String date = arr[0];
        String time = arr[1];
        String[] dateArray = date.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]) - 1;
        int year = Integer.parseInt(dateArray[2]);
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        Date d = new GregorianCalendar(year, month, day, hour, minute).getTime();
        return d;
    }
}

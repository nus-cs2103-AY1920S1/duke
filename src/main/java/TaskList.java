import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Represents the methods of application.
 */
public class TaskList {

    private static DateTimeFormatter time = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> list = null;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public Task get(int i) {
        return list.get(i);
    }

    public Task remove(int i) {
        return list.remove(i);
    }

    public ArrayList<Task> getlist() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task t) {
        list.add(t);
    }

    public void delete(Task t) {
        list.remove(t);
    }
    public void showlist() {
        //String output = "";
        for (int i = 0; i < list.size(); i++) {
            int itemIndex = i + 1;
            String output = itemIndex + "." + list.get(i).toString();
            System.out.println(output);
        }
    }

    public String showlistString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int itemIndex = i + 1;
            str = str + itemIndex + "." + list.get(i).toString()+"\n";
        }
        return str;
    }



    public void timeform(String dateTimeString, Task t) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, time);

            if (t instanceof Event) {
                Event e = (Event) t;
                e.setDateTime(dateTime);
            } else {
                Deadline d = (Deadline) t;
                d.setDateTime(dateTime);
            }
            add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Error " + "Please enter date and time in right format.");
        }
    }
    public String timeformString(String dateTimeString, Task t) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, time);

            if (t instanceof Event) {
                Event e = (Event) t;
                e.setDateTime(dateTime);
            } else {
                Deadline d = (Deadline) t;
                d.setDateTime(dateTime);
            }
            add(t);
            String a ="Got it. I've added this task:\n" + t +"\n";
            String b ="Now you have " + list.size() + " tasks in the list.\n";
            return a+b;
        } catch (Exception e) {
            String c ="Error " + "Please enter date and time in right format.";
            return c;
        }
    }
}

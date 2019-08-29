import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static String[] checklist = new String[100];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns tick or cross symbol.
    public String getStatusIcon() {
        //System.out.println("\u2713");
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;

    }

    public static void printGI() {
        //Duke temp = new Duke();
        Duke.printLine();
        Duke.printIndent();
        System.out.println("Got it. I've added this task:");
    }

    public static void printNumOfTasks() {
        Duke.printIndent();
        System.out.println("Now you have " + Duke.counter + " tasks in the list.");
        Duke.printLine();
    }

    public String toString() {
        return "[" + getStatusIcon() + "]";
    }

    public static void printRemove() {
        Duke.printLine();
        Duke.printIndent();
        Duke.counter--;
        System.out.println("Noted. I've removed this task.");
    }

    public static String formatDate(String date) {
        String formatted = date;
        try {
            Date d = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(date);
            String day = new SimpleDateFormat("dd").format(d);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(d);
            String year = new SimpleDateFormat("yyyy").format(d);
            String time = new SimpleDateFormat("h:mm a").format(d).toLowerCase();
            String endOfDate;
            int dayInteger = Integer.parseInt(day);

            if (dayInteger % 10 == 1 && dayInteger != 11) {
                endOfDate = "st";
            } else if (dayInteger % 10 == 2 && dayInteger != 12) {
                endOfDate = "nd";
            } else if (dayInteger % 10 == 3 && dayInteger != 13) {
                endOfDate = "rd";
            } else {
                endOfDate = "th";
            }

            formatted = dayInteger + endOfDate + " of " + month + " " + year + ", " + time;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return formatted;
    }
}

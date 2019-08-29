import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick or cross symbol.
     *
     * @return A tick or cross to symbolize whether the task has been done.
     */
    public String getStatusIcon() {
        //System.out.println("\u2713");
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Method to mark the task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints a statement informing the user that the bot
     * has added the task into the list.
     */
    public static void printGI() {
        //Duke temp = new Duke();
        Duke.printLine();
        Duke.printIndent();
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static void printNumOfTasks() throws IOException {
        Duke.printIndent();
        System.out.println("Now you have " + Duke.getNumOfTasks() + " tasks in the list.");
        Duke.printLine();
    }

    public String toString() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Prints a statement to tell the user that the task has been removed.
     */
    public static void printRemove() {
        Duke.printLine();
        Duke.printIndent();
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

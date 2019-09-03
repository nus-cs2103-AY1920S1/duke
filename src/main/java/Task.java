import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isCorrectFormat;

    /**
     * Constructor for Task.
     *
     * @param description Takes in a string that is
     * either Todo, Event or Deadline.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isCorrectFormat = false;
    }

    /**
     * Method to get whether that date is in correct format.
     *
     * @return Returns the boolean isCorrectFormat for the date.
     */
    public boolean getIsCorrectFormat() {
        return isCorrectFormat;
    }

    /**
     * Returns tick or cross symbol.
     *
     * @return A tick or cross to symbolize whether the task has been done.
     */
    public String getStatusIcon() {
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
    public void printGI() {
        Ui.printLine();
        Ui.printIndent();
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void printNumOfTasks() throws IOException {
        Ui.printIndent();
        System.out.println("Now you have " +
                Ui.getNumOfTasks() + " tasks in the list.");
        Ui.printLine();
    }

    /**
     * Method to get the String for whether
     * the task is done or not.
     *
     * @return Returns the tick or cross in brackets.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Prints a statement to tell the user that the task has been removed.
     */
    public void printRemove() {
        Ui.printLine();
        Ui.printIndent();
        System.out.println("Noted. I've removed this task.");
    }

    /**
     * Method to format the date into the appropriate format.
     * For example, 10/02/2012 1800 to 10th of February 2012, 6:00 pm.
     *
     * @param date Takes in a valid date to format it.
     * @return Returns the correctly formatted date with the
     * appropriate strings.
     */
    public String formatDate(String date) {
        String formatted = date;
        if (!date.contains(")")) {
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
                isCorrectFormat = true;
                formatted = dayInteger + endOfDate + " of " + month + " " + year + ", " + time;
            } catch (ParseException e) {
                Ui.printIndent();
                System.out.println(e.getMessage());
                Ui.printIndent();
                System.out.println("That is the wrong date format! >:-(");
            }
        } else {
            isCorrectFormat = true;
        }
        return formatted;
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadLine;
    private String interpretedDate;
    private String interpretedTime;

    /**
     * @param taskName task of the Deadline object
     * @param deadLine deadline of the Deadline object in the following format: 2/12/2019 1800
     *
     */

    public Deadline(String taskName, String deadLine) {
        super(taskName);
        this.deadLine = deadLine;
        interpretDeadLine();
    }

    /**
     * Returns the message comprising [D], name of the task of the deadline object and its deadline,
     * together with the a symbol representing if a deadline object is completed or not.
     * [D] represents that our object is a deadline object.
     *
     */
    public String toString() {
        String box;
        String msg = "[D]";
        if (!this.isDone()) {
            box = "[✗]";
        } else {
            box = "[✓]";
        }
        return msg + box + " " + this.getTask() + " (by: " + this.interpretedDate + " at " + this.interpretedTime + ")";
    }

    /**
     * Interprets deadline of Deadline object.
     *eg 2/12/2019 becomes 2 December 2019
     *and 1830 becomes 0630 pm
     */

    public void interpretDeadLine() {
        String[] words = deadLine.split(" ");
        String date = words[0];
        String time = words[1];
        convertDateFormat(date);
        convertTimeFormat(time);
    }

    /**
     * Converts and sets interpretedDate attribute to a newly formatted date. i.e in d MMMM yyyy format.
     *
     * @param date date in old format for a given Deadline object. i.e time is in d/MM/yyyy format.
     *
     */

    public void convertDateFormat(String date) {
        DateTimeFormatter oldDateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate oldDate = LocalDate.parse(date, oldDateFormat);
        String newDate = oldDate.format(newDateFormat);
        this.interpretedDate = newDate;

    }

    /**
     * Converts and sets interpretedTime as a newly formatted time. i.e in hhmm a format.
     *
     * @param oldTime time in old format for a given Deadline object. i.e time is in HHmm format.
     *
     */

    public void convertTimeFormat(String oldTime) {
        DateTimeFormatter oldTimeFormat = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter newTimeFormat = DateTimeFormatter.ofPattern("hhmm a");
        LocalTime timeObj = LocalTime.parse(oldTime, oldTimeFormat);
        String newTime = timeObj.format(newTimeFormat);
        this.interpretedTime = newTime;
    }

    /**
     * Returns a message by Duke, when Duke has added a Deadline object.
     *
     * @param size Number of tasks managed by Duke after we have added a Deadline object.
     *
     */

    public String addDeadlineMsg(int size) {
        String message = "Got it. I've added this task: \n";
        message = message + "  " + this.toString() + "\n";
        String end = " tasks in the list.";
        if (size == 1) {
            end = " task in the list.";
        }
        return message + "Now you have " + size + end;
    }

}

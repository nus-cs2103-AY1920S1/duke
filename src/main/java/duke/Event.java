package duke;

public class Event extends Task {

    Date date;
    Time time;

    /**
     * Creates Events (also a Task).
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param date Date at which deadline occurs.
     * @param time Time at which deadline occurs.
     * @param type String indicates task type, in this case "E".
     * @param done boolean indicates whether the task is done.
     */
    Event(int num, String task, Date date, Time time, String type, boolean done) {
        super(num, task, type, done);
        this.time = time;
        this.date = date;
    }

    /**
     * Creates Events (also a Task).
     * Done status is set as not done by default.
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param date Date at which deadline occurs.
     * @param time Time at which deadline occurs.
     * @param type String indicates task type, in this case "E".
     */
    Event(int num, String task, Date date, Time time, String type) {
        super(num, task, type);
        this.time = time;
        this.date = date;
    }

    /**
     * Formats the event for printing.
     * @return String in the form for printing
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s %s)", done ? "✓" : "✗",task, date, time);
    }

    /**
     * Formats task to be written in given file.
     * @return formatted string for writing in file.
     */
    @Override
    public String fileFormat() {
        return String.format("E | %s | %s | %s %s", done ? "1" : "0", task, date, time);
    }
}

package task;

import exceptions.MissingInputException;

public class Event extends Task {

    /**
     * Creates Events (also a Task).
     *
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param date Date at which deadline occurs.
     * @param time Time at which deadline occurs.
     * @param type String indicates task type, in this case "E".
     * @param done boolean indicates whether the task is done.
     */
    public Event(int num, String task, DukeDate date, DukeTime time,
                 String type, boolean done) throws MissingInputException {
        super(num, task, date, time, type, done);
    }

    /**
     * Creates Events (also a Task).
     * Done status is set as not done by default.
     *
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param date Date at which deadline occurs.
     * @param time Time at which deadline occurs.
     * @param type String indicates task type, in this case "E".
     */
    public Event(int num, String task, DukeDate date, DukeTime time, String type) throws MissingInputException {
        super(num, task, date, time, type);
    }

    /**
     * Formats the event for printing.
     *
     * @return String in the form for printing.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s %s)", done ? "Y" : "N",task, date, time);
    }

    /**
     * Formats task to be written in given file.
     *
     * @return formatted string for writing in file.
     */
    @Override
    public String fileFormat() {
        return String.format("E | %s | %s | %s %s", done ? "1" : "0", task, date, time);
    }
}

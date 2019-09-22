package task;

import exceptions.MissingInputException;

public class Deadline extends Task {

    /**
     * Creates Deadline (also a Task).
     *
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param date Date at which deadline occurs.
     * @param time Time at which deadline occurs.
     * @param type String indicates task type, in this case "D".
     * @param done boolean indicates whether the task is done.
     */
    public Deadline(int num, String task, DukeDate date, DukeTime time,
                    String type, boolean done) throws MissingInputException {
        super(num, task, date, time, type, done);
    }

    /**
     * Creates Deadline (also a Task).
     * Done status is set as not done by default.
     *
     * @param num Number in the list.
     * @param task String indicates description of task.
     * @param date Date at which deadline occurs.
     * @param time Time at which deadline occurs.
     * @param type String indicates task type, in this case "D".
     */
    public Deadline(int num, String task, DukeDate date, DukeTime time, String type) throws MissingInputException {
        super(num, task, date, time, type);
    }

    /**
     * Formats the deadline for printing.
     *
     * @return String in the form for printing
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s %s)", done ? "Y" : "N",task, date, time);
    }

    /**
     * Formats the deadline for writing in the text file.
     *
     * @return String in the form writing in text file.
     */
    @Override
    public String fileFormat() {
        return String.format("D | %s | %s | %s %s", done ? "1" : "0", task, date, time);
    }

}

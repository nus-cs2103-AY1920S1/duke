package duke.task;

import java.time.LocalDateTime;

/**
 * Deadline object representing deadlines. A subclass of Task but contains an extra date attribute that represents
 * the due date of the task. The date can be added in the Duke UI by adding /by , followed by a date in the format of
 * e.g 18/02/2019 1800.
 */
public class Deadline extends Task {
    /** LocalDateTime object representing date of the deadline */
    private LocalDateTime date;

    /**
     * Creates a Deadline object that stores the type of task it is, i.e "D", as well as a description and
     * a LocalDateTime object representing the deadline's due date.
     *
     * @param type Type of task.
     * @param name Description of the deadline.
     * @param date LocalDateTime object representing due date of the task.
     */
    public Deadline(String type, String name, LocalDateTime date) {
        super(type, name);
        this.date = date;
    }

    /**
     * Creates a Deadline object that stores the type of task it is, i.e "D", as well as a description and
     * a LocalDateTime object representing the deadline's due date. This constructor is used when loading tasks
     * from the datafile as it checks if the task has already been marked as done.
     *
     * @param type Type of task.
     * @param done Boolean representing whether the task is done.
     * @param name Description of the deadline.
     * @param date LocalDateTime object representing due date of the task.
     */
    public Deadline(String type, String done, String name, String priority, LocalDateTime date) {
        super(type, done, name, priority);
        this.date = date;
    }

    /**
     * Returns a formatted string representing the state of the task to be stored in a data file.
     *
     * @return String formatted for storing in a file.
     */
    @Override
    public String fileFormat() {
        return String.format("%s | %s | %s | %s | %s\n",
                type, isDone ? "1" : "0", name, FILE_DATE_TIME.format(date).toString(), priority == null ? "none" : priority);

    }

    /**
     * String representing a task and its state when user lists all tasks.
     *
     * @return String in the format for the list command.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s) %s",
                isDone ? "v" : "x", name, DATE_TIME.format(date).toString(), priority == null ? "" : "<Priority: " + priority + ">");

    }
}

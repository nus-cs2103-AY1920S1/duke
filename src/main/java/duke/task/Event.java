package duke.task;

import java.time.LocalDateTime;

/**
 * Event object representing events. A subclass of Task but contains an extra date attribute that represents
 * the date and time at which the event is planned to happen.
 * The date can be added in the Duke UI by adding /at , followed by a date in the format of e.g 18/02/2019 1800.
 */
public class Event extends Task {
    /** LocalDateTime object representing date of the event */
    private LocalDateTime date;

    /**
     * Creates an Event object that stores the type of task it is, i.e "E", as well as a description and
     * a LocalDateTime object representing the event's date and time of occurrence.
     *
     * @param type Type of task.
     * @param name Description of the event.
     * @param date LocalDateTime object representing date and time of occurrence of the event.
     */
    public Event(String type, String name, LocalDateTime date) {
        super(type, name);
        this.date = date;
    }

    /**
     * Creates an Event object that stores the type of task it is, i.e "E", as well as a description and
     * a LocalDateTime object representing the event's date and time of occurrence.This constructor is used
     * when loading tasks from the datafile as it checks if the task has already been marked as done.
     *
     * @param type Type of task.
     * @param done Boolean representing whether the task is done.
     * @param name Description of the event.
     * @param date LocalDateTime object representing date and time of occurrence of the event.
     */
    public Event(String type, String done, String name, String priority, LocalDateTime date) {
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
        return String.format("[E][%s] %s (at: %s) %s",
                isDone ? "v" : "x", name, DATE_TIME.format(date).toString(), priority == null ? "" : "<Priority: " + priority + ">");

    }
}

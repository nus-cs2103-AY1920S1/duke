package task;

import java.time.LocalDateTime;

/**
 * Represents Tasks with associated date and time.
 */
public class DateTask extends Task {

    private LocalDateTime date;

    /**
     * Creates the DateTask object.
     *
     * @param description Task message.
     * @param date        Date for task.
     * @param isDone      Marks the task as done nor not done.
     */
    DateTask(String description, LocalDateTime date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns the LocalDateTime object of the task.
     *
     * @return LocalDateTime object of the task.
     */
    public LocalDateTime getDate() {
        return this.date;
    }
}

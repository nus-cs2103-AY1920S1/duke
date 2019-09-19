package duke.extension;

import duke.task.Task;

import java.util.Date;
import java.util.Timer;

/**
 * The user may set a reminder for the task. When a reminder is set for a specified task, a popup alert is set in the
 * future that will alert the user upon the specified date time.
 */
public class Reminder {

    /**
     * This is the timer for the reminder. It will run when the specified date time is reached.
     */
    private Timer timer;

    /**
     * This is the date time for the reminder to be triggered.
     */
    private Date dateToRemind;

    /**
     * Constructs a new reminder for the task for the specified task with the specified date to be be reminded.
     * @param task the task whose reminder has been set
     * @param dateToRemind the date to trigger the reminder
     */
    public Reminder(Task task, Date dateToRemind) {
        this.dateToRemind = dateToRemind;
        this.timer = new Timer();
        this.timer.schedule(new NotificationAlert(task), dateToRemind);
    }

    /**
     * Gets the date of the reminder.
     * @return the date specified for the reminder
     */
    public Date getDate() {
        return dateToRemind;
    }

    /**
     * Gets a string representation of the reminder.
     * @return a string representation of the reminder
     */
    @Override
    public String toString() {
        return "⏰ " + dateToRemind.toString() + " ⏰";
    }

}

package duke.extension;


import java.util.Date;
import java.util.Optional;
import java.util.Timer;

/**
 * The user may set a reminder for the task. When a reminder is set for a specified task, a popup alert is set in the
 * future that will alert the user upon the specified date time.
 */
public class Reminder {

    private Optional<Date> reminderDate = Optional.empty();

    private String taskInfo;
    /**
     * This is the timer for the reminder. It will run when the specified date time is reached.
     */
    private Timer timer;


    /**
     * Constructs a new reminder for the task for the specified task with the specified date to be be reminded.
     * @param taskInfo the task whose reminder has been set
     */
    public Reminder(String taskInfo) {
        this.taskInfo = taskInfo;
        this.timer = new Timer();
    }

    /**
     * Sets a timer for the reminder at the specified date by overwriting the previous timer.
     * @param dateToRemind the date to display the reminder
     */
    public void setTimer(Date dateToRemind) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new NotificationAlert(taskInfo), dateToRemind);
        reminderDate = Optional.ofNullable(dateToRemind);
    }

    /**
     * Clears the optional date value if it is overdue.
     */
    public void clearOptionalDateIfOverdue() {
        if (reminderDate.isPresent()) {
            if (reminderDate.get().compareTo(new Date()) < 0) {
                reminderDate = Optional.empty();
            }
        }
    }

    /**
     * Gets the optional date of the reminder. A date for the reminder will be present if the timer for the reminder
     * has been set
     * @return the date set for the timer if present
     */
    public Optional<Date> getOptionalDate() {
        clearOptionalDateIfOverdue();
        return reminderDate;
    }

    /**
     * Gets a string representation of the reminder.
     * @return a string representation of the reminder
     */
    @Override
    public String toString() {
        clearOptionalDateIfOverdue();
        if (reminderDate.isPresent()) {
            return "Reminder set to: " + reminderDate.get().toString();
        } else {
            return "";
        }
    }

}

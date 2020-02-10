package czkay.duke.model.reminder;

import czkay.duke.model.task.Task;
import czkay.duke.model.task.TimedTask;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * A reminder of an upcoming task that the user had inputted.
 */
public class Reminder implements Serializable {
    private TimedTask upcomingTask;
    private String totalTimeDifference;
    private static final int THRESHOLD = 7;
    private boolean isOver;
    private boolean isValid;

    private Reminder(TimedTask task) {
        upcomingTask = task;
        checkValidity(task.getTimestamp());
    }

    /**
     * Calculates the time left before the task has to be completed.
     *
     * @param timestamp The timestamp of the timed task.
     */
    private void checkValidity(LocalDateTime timestamp) {
        LocalDateTime tempDateTime = LocalDateTime.now();

        long daysLeft = tempDateTime.until(timestamp, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(daysLeft);

        long hoursLeft = tempDateTime.until(timestamp, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hoursLeft);

        long minutesLeft = tempDateTime.until(timestamp, ChronoUnit.MINUTES);

        totalTimeDifference = String.format("%d days, %d hours and %d minutes",
                Math.abs(daysLeft), Math.abs(hoursLeft), Math.abs(minutesLeft));

        isOver = (daysLeft < 0 || hoursLeft < 0 || minutesLeft < 0);

        isValid = (daysLeft < THRESHOLD);
    }

    /**
     * Creates a reminder only if the task is deemed as upcoming.
     *
     * @param task The task which may or may not be upcoming.
     * @return An Optional Reminder which will contain a reminder if the task is upcoming.
     */
    public static Optional<Reminder> createReminderIfValid(Task task) {
        if (task.isTimed() && !(task.isDone())) {
            Reminder potentialReminder = new Reminder((TimedTask) task);
            return (potentialReminder.isValid) ? Optional.of(potentialReminder) : Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    /**
     * Formats the reminder into a readable form for the user.
     *
     * @return The reminder message.
     */
    @Override
    public String toString() {
        if (isOver) {
            return String.format(
                    "The task, %s, has already gone past its scheduled time by %s!",
                    upcomingTask.getTaskDescription(), totalTimeDifference);
        } else {
            return String.format(
                    "You have %s left to complete the task: %s!",
                    totalTimeDifference, upcomingTask.getTaskDescription());
        }
    }

}

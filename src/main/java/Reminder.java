import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * A reminder of an upcoming task that the user had inputted.
 */
public class Reminder implements Serializable {
    private TimedTask upcomingTask;
    private String totalTimeLeft;
    private static final int THRESHOLD = 7;
    private long daysLeft;

    private Reminder(TimedTask task) {
        upcomingTask = task;
        calculateTimeLeft(task.getTimestamp());
    }

    /**
     * Calculates the time left before the task has to be completed.
     *
     * @param timestamp The timestamp of the timed task.
     */
    private void calculateTimeLeft(LocalDateTime timestamp) {
        LocalDateTime tempDateTime = LocalDateTime.now();
        daysLeft = tempDateTime.until(timestamp, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(daysLeft);

        long hoursLeft = tempDateTime.until(timestamp, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hoursLeft);

        long minutesLeft = tempDateTime.until(timestamp, ChronoUnit.MINUTES);

        totalTimeLeft = String.format("%d days, %d hours and %d minutes left", daysLeft, hoursLeft, minutesLeft);
    }

    /**
     * Creates a reminder only if the task is deemed as upcoming.
     *
     * @param task The task which may or may not be upcoming.
     * @return An Optional Reminder which will contain a reminder if the task is upcoming.
     */
    static Optional<Reminder> createReminderIfValid(Task task) {
        if (task.isTimed()) {
            Reminder potentialReminder = new Reminder((TimedTask) task);
            return (potentialReminder.daysLeft <= THRESHOLD) ? Optional.of(potentialReminder) : Optional.empty();
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
        return String.format("You have %s to complete the task: %s!", totalTimeLeft, upcomingTask.getTaskDescription());
    }

}

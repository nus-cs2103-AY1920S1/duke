import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Reminder implements Serializable {
    private TimedTask upcomingTask;
    private String totalTimeLeft;
    private static final int THRESHOLD = 7;
    private long daysLeft;

    private Reminder(TimedTask task) {
        upcomingTask = task;
        calculateTimeLeft(task.getTimestamp());
    }

    private void calculateTimeLeft(LocalDateTime timestamp) {
        LocalDateTime tempDateTime = LocalDateTime.now();
        daysLeft = tempDateTime.until(timestamp, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(daysLeft);

        long hoursLeft = tempDateTime.until(timestamp, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hoursLeft);

        long minutesLeft = tempDateTime.until(timestamp, ChronoUnit.MINUTES);

        totalTimeLeft = String.format("%d days, %d hours and %d minutes left", daysLeft, hoursLeft, minutesLeft);
    }

    static Optional<Reminder> createReminderIfValid(Task task) {
        if (task.isTimed()) {
            Reminder potentialReminder = new Reminder((TimedTask) task);
            return (potentialReminder.daysLeft <= THRESHOLD) ? Optional.of(potentialReminder) : Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return String.format("You have %s to complete the task: %s!", totalTimeLeft, upcomingTask.getTaskDescription());
    }

}

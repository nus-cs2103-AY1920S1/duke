import java.time.LocalDateTime;

/**
 * Represents a deadline to meet that contains both a description and deadline time.
 */
public class DeadlineTask extends Task {
    /**
     * The time of the DeadlineTask, as inputted by the user.
     */
    protected LocalDateTime time;
    
    /**
     * Creates a DeadlineTask.
     *
     * @param description The description of the DeadlineTask, as inputted by the user.
     * @param time The time of the DeadlineTask, as inputted by the user.
     */
    public DeadlineTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    
    /**
     * Returns a default formatted String for writing data to the user's hard drive.
     * DeadlineTask String includes tag identifying this as a DeadlineTask, in addition to the Task default
     * formatted String.
     * Also includes the value of the deadline's time, formatted as a String.
     *
     * @return Returns a String.
     */
    @Override
    public String formattedString() {
        return "D | " + super.formattedString() + " | " + time.getDayOfMonth() + "/" + time.getMonthValue() + "/"
                + String.format("%04d", time.getYear()) + " " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + "\n";
    }
    
    /**
     * Returns a default String for printing to the user's console.
     * DeadlineTask String includes tag identifying this as a DeadlineTask, in addition to the Task default String.
     * Also includes the value of the deadline's time, formatted as a String.
     *
     * @return Returns a String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.getDayOfMonth() + " of " + time.getMonth() + ", "
                + time.getYear() + ", at " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + " hrs)";
    }
}

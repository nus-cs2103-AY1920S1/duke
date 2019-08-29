import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline
     * @param description
     * @param by time in the format of dd/MM/yyyy HHmm
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.TIME_FORMATTER);
    }

    /**
     * Another constructor for Deadline
     * @param description
     * @param by time in the format of dd/MM/yyyy HHmm
     * @param isDone whether Deadline is completed or not
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, Task.TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * @return String that would be written into data file
     */
    @Override
    public String toDataString() {
        String[] dateTimeSplit = by.toString().split("T");

        String date = dateTimeSplit[0];
        String time = dateTimeSplit[1];

        String[] dateSplit = date.split("-");
        String[] timeSplit = time.split(":");
        String dataBy = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0] + " "
                + timeSplit[0] + timeSplit[1];
        return "D | " + super.toDataString() + " | " + dataBy;
    }
}

import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime at;

    /**
     * Constructor for event
     * @param description
     * @param at time in the format of dd/MM/yyyy HHmm
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, Task.TIME_FORMATTER);
    }

    /**
     * Constructor for event
     * @param description
     * @param at time in the format of dd/MM/yyyy HHmm
     * @param isDone whether Event is completed or not
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, Task.TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * @return String that would be written into data file
     */
    @Override
    public String toDataString() {
        String[] dateTimeSplit = at.toString().split("T");

        String date = dateTimeSplit[0];
        String time = dateTimeSplit[1];

        String[] dateSplit = date.split("-");
        String[] timeSplit = time.split(":");
        String dataAt = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0] + " "
                + timeSplit[0] + timeSplit[1];
        return "E | " + super.toDataString() + " | " + dataAt;
    }
}

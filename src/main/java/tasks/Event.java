package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    LocalDateTime date1;
    LocalDateTime date2;

    /**
     * This is a constructor for tasks.Deadline.
     * @param description description of task
     * @param at start and end time of event
     */

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.symbol = "E";
    }

    /**
     * This method is used to generate the date of start and end
     * of the event as a LocalDateTime object.
     *
     */
    public void getDate() {
        String[] dateArray = at.split(" ");
        //DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date1 = date1.parse(dateArray[0],formatter);
        date2 = date1.parse(dateArray[0],formatter);
        date1 = date1.withHour(Integer.parseInt(dateArray[1]) / 100).withMinute(Integer.parseInt(dateArray[1]) % 100);
        date2.withHour(Integer.parseInt(dateArray[2]) / 100).withMinute(Integer.parseInt(dateArray[2]) % 100);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
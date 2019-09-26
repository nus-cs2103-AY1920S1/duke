package tasks;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    LocalDateTime date1;
    LocalDateTime date2;

    /**
     * This is a constructor for tasks.Event.
     *
     * @param description description of task
     * @param at          start and end time of event
     */

    public Event(String description, String at) {
        super(description);
        assert at != null;
        this.at = at;
        super.symbol = "E";
        getDate();
    }

    /**
     * This method is used to generate the date of start and end
     * of the event as a LocalDateTime object.
     */
    public void getDate() {
        String[] dateArray = at.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate d1 = LocalDate.parse(dateArray[0], formatter);
        LocalDate d2 = LocalDate.parse(dateArray[0], formatter);
        date1 = d1.atTime(Integer.parseInt(dateArray[1]) / 100, Integer.parseInt(dateArray[1]) % 100);
        date2 = d2.atTime(Integer.parseInt(dateArray[2]) / 100, Integer.parseInt(dateArray[2]) % 100);
    }

    @Override
    public LocalDateTime getDateTime() {
        return date1;
    }

    @Override
    public String getExtraInfo() {
        return at;
    }

    @Override
    public void postpone(int daysToPostpone,
                         int hoursToPostpone, int minutesToPostpone) {

        date1 = date1.plus(Duration.ofDays(daysToPostpone))
                .plus(Duration.ofHours(hoursToPostpone)).plus(Duration.ofMinutes(minutesToPostpone));
        date2 = date2.plus(Duration.ofDays(daysToPostpone))
                .plus(Duration.ofHours(hoursToPostpone)).plus(Duration.ofMinutes(minutesToPostpone));

        assert date1 != null;
        assert date2 != null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + date1 + " to " + date2 + ")" + super.getNotes();
    }
}
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeRangeHelper {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String displayText;

    public DateTimeRangeHelper(LocalTime start, LocalTime end, LocalDate date, String text) {
        startTime = start;
        endTime = end;
        this.date = date;
        displayText = text;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalTime getEndTime(){
        return endTime;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getText(){
        return displayText;
    }
}

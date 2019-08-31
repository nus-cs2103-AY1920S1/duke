import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;



public class DateTime{
    private LocalDateTime start;
    private LocalTime endTime;
    private String display;
    private static DateTimeFormatter getStart = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HHmm");

    public static DateTime setDeadline(String date_time){
        DateTime deadline = new DateTime();
        deadline.start = LocalDateTime.parse(date_time, getStart);
        deadline.display = deadline.start.format(getStart);
        return deadline;
    }

    public static DateTime readDeadLine(String date_time){
        DateTime deadline = new DateTime();
        deadline.start = LocalDateTime.parse(date_time, getStart);
        deadline.display = deadline.start.format(getStart);
        return deadline;
    }

    public static DateTime setEventTime (String date_time) throws DukeException{
        DateTime event = new DateTime();
        int divider = date_time.indexOf("-");
        if (divider == -1 || (divider == date_time.length() -1)){
            throw new DukeException("Need to have end time.");
        }
        String end = date_time.substring(divider + 1, date_time.length());
        event.start = LocalDateTime.parse(date_time.substring(0, divider), getStart);
        event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        event.display = event.start.format(getStart) + "-" + event.endTime.format(getTime);
        return event;
    }

    public static DateTime readEventTime(String date_time) throws DukeException{
        DateTime event = new DateTime();
        int divider = date_time.indexOf("-");
        if (divider == -1 || (divider == date_time.length() -1)){
            throw new DukeException("Need to have end time.");
        }
        String end = date_time.substring(divider + 1, date_time.length());
        event.start = LocalDateTime.parse(date_time.substring(0, divider), getStart);
        event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        event.display = event.start.format(getStart) + "-" + event.endTime.format(getTime);
        return event;
    }
    private static String getDayOfMonth(String dd) {
        if (dd.substring(0,1).equals("0")){
            dd = dd.substring(1,dd.length());
        }
        int day = Integer.parseInt(dd);
        if (day >= 11 && day <= 13) {
            return day + "th";
        }
        switch (day % 10) {
            case 1:  return day + "st";
            case 2:  return day + "nd";
            case 3:  return day + "rd";
            default: return day + "th";
        }
    }

    public String toString(){
        return display;
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;



public class DateTime{
    private LocalDateTime start;
    private LocalTime endTime;
    private String display;
    private static DateTimeFormatter getDay = DateTimeFormatter.ofPattern("dd");
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter getMonth= DateTimeFormatter.ofPattern("MMM");

    public static DateTime setDeadline(String date_time){
        DateTime deadline = new DateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        deadline.start = LocalDateTime.parse(date_time, formatter);
        deadline.display = deadline.start.format(getMonth) + " "
                + getDayOfMonth(deadline.start.format(getDay)) + " "
                + deadline.start.format(getTime);
        return deadline;
    }

    public static DateTime setEventTime (String date_time) throws DukeException{
        DateTime event = new DateTime();
        int divider = date_time.indexOf("-");
        if (divider == -1 || (divider == date_time.length() -1)){
            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Need to have end time.\n" + (new Border()));
        }
        String end = date_time.substring(divider + 1, date_time.length());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        event.start = LocalDateTime.parse(date_time.substring(0, divider), formatter);
        event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        event.display = event.start.format(getMonth) + " "
                + getDayOfMonth(event.start.format(getDay)) + " "
                + event.start.format(getTime) + "-" + event.endTime.format(getTime);
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

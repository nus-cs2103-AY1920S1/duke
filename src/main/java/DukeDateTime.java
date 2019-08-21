import java.time.LocalDate;
import java.time.LocalTime;

public class DukeDateTime {
    public final LocalTime time;
    public final LocalDate date;

    //Date should be received in DD/MM/YYYY format
    //Time should be received in Military Time (HHMM)
    public DukeDateTime (String dateString, String militaryTimeString) {
        date = parseDate(dateString);
        time = parseMilitaryTime(militaryTimeString);
    }

    private LocalDate parseDate (String dateString) {
        String [] dateStringSplit = dateString.split("/");
        LocalDate localDate = LocalDate.of(Integer.parseInt(dateStringSplit[2]), 
                                           Integer.parseInt(dateStringSplit[1]),
                                           Integer.parseInt(dateStringSplit[0]));
        
        return localDate;
    }

    private LocalTime parseMilitaryTime (String militaryTimeString) {
        int hourOfDay = Integer.parseInt(militaryTimeString.substring(0, 2));
        int minuteOfDay = Integer.parseInt(militaryTimeString.substring(2, 4));
        LocalTime localTime = LocalTime.of(hourOfDay, minuteOfDay);

        return localTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String dateString = date.toString();
        sb.append(dateString.substring(8, 10));
        sb.append("/");
        sb.append(dateString.substring(5, 7));
        sb.append("/");
        sb.append(dateString.substring(0, 4));

        sb.append(" ");

        String timeString = time.toString();
        sb.append(timeString.substring(0, 2));
        sb.append(timeString.substring(3, 5));

        return sb.toString();
    }
}
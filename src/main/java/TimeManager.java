import java.text.Format;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TimeManager {

    public LocalDateTime getTime(String s) throws IllegalTimeFormatException{
        String[] dateTime = s.trim().split(" ");
        String[] date = dateTime[0].split("/");
        String[] temp = new String[5];
        for (int i = 0; i < 5; i++) {
            if (i < date.length) {
                temp[i] = date[i];
            } else {
                temp[i] = "00";
            }
        }
        date = temp;
        String time;
        if (date[0].length() > 2) {
            // time is simply s
            time = dateTime[0];
            date[0] = "00";
            date[3] = time.substring(0, 2);
            date[4] = time.substring(2);
        } else {
            if (dateTime.length > 1) {
                // time is stored in dateTime[1]
                time = dateTime[1];
                try {
                    date[3] = time.substring(0, 2);
                    date[4] = time.substring(2);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new IllegalTimeFormatException(
                            "☹ Sorry, I couldn't recognise the time. Enter time in the format of 'hhmm' :D");
                }
            } else {
                // no time value
            }
        }
        return toTime(date);
    }

    private LocalDateTime toTime(String[] times) throws IllegalTimeFormatException{
        try {
            LocalDateTime current = LocalDateTime.now();
            int year = times[2].equals("00") ? current.getYear() : Integer.parseInt(times[2]);
            int month = times[1].equals("00") ? current.getMonth().getValue() : Integer.parseInt(times[1]);
            int day = times[0].equals("00") ? current.getDayOfMonth() : Integer.parseInt(times[0]);
            int hour = Integer.parseInt(times[3]);
            int minute = Integer.parseInt(times[4]);
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            throw new IllegalTimeFormatException(
                    "☹ Sorry, I couldn't recognise the time.\n" +
                            "     Try enter in the format of 'dd/MM/yy hhmm' :D");
        } catch (NumberFormatException nfe) {
            throw new IllegalTimeFormatException(
                    "☹ Sorry, only numbers can be recognised for time.\n" +
                            "     Try enter in the format of 'dd/MM/yy hhmm' :D");
        }
    }

    public String printTime(LocalDateTime dt) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_@_hh:mma");
        return dt.format(customFormatter);
    }
}

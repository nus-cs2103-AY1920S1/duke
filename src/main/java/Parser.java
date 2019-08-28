import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {
    //example input: 2/12/2019 1800
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            String[] strSplit = dateTimeStr.split(" ");
            String[] date = strSplit[0].split("/");
            String time = strSplit[1];

            int year = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[0]);
            int hours = Integer.parseInt(time.substring(0, 2));
            int mins = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hours, mins);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (NullPointerException e) {

        } catch (DateTimeException e) {
            System.out.println("Invalid Date-Time format. Error Message: " + e.getMessage());
            System.out.println("Please use DD/MM/YYYY HHMM E.g. [2/12/2019 1800]");
        }
        return null;
    }

    public static String toFileDateTime(LocalDateTime dateObj) {
        return dateObj.getDayOfMonth() + "/" + dateObj.getMonthValue() + "/" + dateObj.getYear() + " "
                + String.format("%02d", dateObj.getHour())
                + String.format("%02d", dateObj.getMinute());
    }

    public static String printDate(LocalDateTime dateObj) {
        return dateObj.getDayOfMonth() + " " + dateObj.getMonth() +
                " " + dateObj.getYear() + " " + String.format("%02d", dateObj.getHour())
                + String.format("%02d", dateObj.getMinute());
    }


}

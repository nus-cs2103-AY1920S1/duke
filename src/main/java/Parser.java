import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    public static String getFirstWord(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public static String excludeFirstWord(String fullCommand, String firstWord) {
        return fullCommand.replaceFirst(firstWord, "").trim();
    }

    public static String[] splitIntoNameAndTime(String string, String regex) {
        return string.split(regex, 2);
    }

    /* Change user input time format(2/12/2019 1800) to LocalDateTime format */
    public static LocalDateTime changeToDateTimeFormat(String dateTime) {
        String date = dateTime.split(" ")[0];
        String time = dateTime.split(" ")[1];
        LocalDate localDate = LocalDate.of(Integer.parseInt(date.split("/")[2]),
                Integer.parseInt(date.split("/")[1]),
                Integer.parseInt(date.split("/")[0]));

        LocalTime localTime = LocalTime.of(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2, 4)));
        return LocalDateTime.of(localDate, localTime);
    }
}

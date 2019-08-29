import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A class which deals with making sense of the user command
 */

public class Parser {
    /**
     * Returns the first word in a string
     *
     * @param fullCommand Full command given by user
     * @return First word in the command
     */
    public static String getFirstWord(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Exclude the first word in a string and returns remaining string
     *
     * @param fullCommand Full command given by user
     * @param firstWord First word to be removed from full command
     * @return Returns string with first word excluded
     */
    public static String excludeFirstWord(String fullCommand, String firstWord) {
        return fullCommand.replaceFirst(firstWord, "").trim();
    }

    /**
     * Exclude the first word in a string and returns remaining string
     *
     * @param string String containing name, regex and time
     * @param regex the string between name and time
     * @return Returns and array of String containing name and time
     */
    public static String[] splitIntoNameAndTime(String string, String regex) {
        return string.split(regex, 2);
    }

    /**
     * Change user input time format(2/12/2019 1800) to LocalDateTime format
     *
     * @param dateTime A specific format of String containing date and time
     * @return Returns the corresponding LocalDateTime
     */
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

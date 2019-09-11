package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeParser {

    /**
     * Validates the format of the given string as a proper Date format.
     * @param dateToValidate - String containing expected date
     * @throws WrongDateFormatException - thrown when the given String does not comply with Date formet
     */
    public static void validateDateFormat(String dateToValidate) throws WrongDateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        //To make strict date format validation
        formatter.setLenient(false);
        try {
            formatter.parse(dateToValidate);
        } catch (ParseException e) {
            throw new WrongDateFormatException(dateToValidate);
        }
    }
}

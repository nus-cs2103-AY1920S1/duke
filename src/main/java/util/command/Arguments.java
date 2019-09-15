package util.command;

import error.command.DateTimeExtractionException;
import error.datetime.UnknownDateTimeException;
import util.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arguments {

    private final String dateTimeRegex =
            "(([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})\\s?([0-9]{4}))";
    private static final String CANNOT_FIND_DATETIME_MESSAGE = "☹ OOPS!!! Unable to locate any datetimes!! :-(";



    private String arguments;

    protected Arguments(String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return arguments;
    }

    public boolean isEmpty() {
        return arguments.equals("");
    }

    public List<LocalDateTime> extractLocalDateTime(int numDates) throws UnknownDateTimeException, DateTimeExtractionException {

        List<String> dateTimePatterns;
        List<LocalDateTime> foundDateTimes = new ArrayList<>();

        if (numDates == 0) {
            // return empty result
            return foundDateTimes;
        }

        // at most 2 dates can be extracted
        try {

            if (numDates == 1) {
                Pattern pattern = Pattern.compile(dateTimeRegex);
                Matcher matcher = pattern.matcher(arguments);

                dateTimePatterns = findmatchingPatterns(arguments, dateTimeRegex);

                // add last identified datetime to result
                String lastDateTimePattern = dateTimePatterns.get(dateTimePatterns.size() - 1);
                foundDateTimes.add(DateTime.parse(lastDateTimePattern));

            } else {

                // date arguments for tasks with 2 dates will be separated by a "to"
                String regex = "(" + dateTimeRegex + "\\s?to\\s?" + dateTimeRegex + ")";

                dateTimePatterns = findmatchingPatterns(arguments, regex);

                // parse last identified matching regex into the two datetimes
                String lastDateTimePairPattern = dateTimePatterns.get(dateTimePatterns.size() - 1);
                String[] dateTimes = lastDateTimePairPattern.split("\\s?to\\s?");

                foundDateTimes.add(DateTime.parse(dateTimes[0]));
                foundDateTimes.add(DateTime.parse(dateTimes[1]));
            }

            arguments = arguments.replaceAll(dateTimePatterns.get(dateTimePatterns.size() - 1), "").trim();

            return foundDateTimes;

        } catch (IndexOutOfBoundsException e) {
            throw new DateTimeExtractionException(CANNOT_FIND_DATETIME_MESSAGE);
        }
    }

    private List<String> findmatchingPatterns(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        List<String> dateTimePatterns = new ArrayList<>();

        // find all substrings that match regex pattern
        while (matcher.find()) {
            dateTimePatterns.add(matcher.group(1));
        }

        return dateTimePatterns;
    }

}

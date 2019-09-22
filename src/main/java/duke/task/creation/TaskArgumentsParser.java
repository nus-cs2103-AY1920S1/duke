package duke.task.creation;

import error.datetime.UnknownDateTimeException;
import util.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used to parse task arguments into its details and corresponding date times. To utilize this class,
 * you have to instantiate a new TaskArgumentsParser with the arguments to be used and the number of dates that
 * should be extracted.
 */
class TaskArgumentsParser {
    private String arguments;
    private final String dateTimeRegex;
    private final String dateTimeRegexWithToSeparator;
    private final String dateTimeRegexWithSpaceSeparator;
    private int numDates;

    TaskArgumentsParser(String arguments, int numDates) {
        this.arguments = arguments;
        this.dateTimeRegex = RecognizedDateTime.DATE_TIME_REGEX;
        this.dateTimeRegexWithToSeparator = "(" + dateTimeRegex + "\\s?to\\s?" + dateTimeRegex + ")";
        this.dateTimeRegexWithSpaceSeparator = "(" + dateTimeRegex + "\\s" + dateTimeRegex + ")";
        this.numDates = numDates;
    }

    /**
     * Parses the arguments into a friendly TaskArgument class that contains the necessary fields required for Task
     * creation.
     * @return a TaskArguments instance
     * @throws UnknownDateTimeException if unable to extract the necessary DateTimes.
     */
    TaskArguments parse() throws UnknownDateTimeException {
        List<LocalDateTime> extractedLocalDateTimes = this.extractLocalDateTime();
        return new TaskArguments(arguments, extractedLocalDateTimes);
    }

    private List<LocalDateTime> extractLocalDateTime() throws UnknownDateTimeException {
        List<LocalDateTime> foundDateTimes = new ArrayList<>();
        List<String> foundPatterns = new ArrayList<>();

        if (numDates == 0) {
            return foundDateTimes;
        }

        if (numDates == 1) {
            foundPatterns = findMatchingPatterns(arguments, dateTimeRegex);

            // chooses last matching datetime as the datetime argument
            String lastDateTimePattern = foundPatterns.get(foundPatterns.size() - 1);
            foundDateTimes.add(DateTime.parse(lastDateTimePattern));
        } else {
            String doubleDateTimeRegex = "(" + dateTimeRegexWithToSeparator + "|" + dateTimeRegexWithSpaceSeparator + ")";

            foundPatterns = findMatchingPatterns(arguments, doubleDateTimeRegex);
            String lastDateTimePattern = foundPatterns.get(foundPatterns.size() - 1);
            for (String dateTimePattern : this.splitDoubleDateTimePattern(lastDateTimePattern)) {
                foundDateTimes.add(DateTime.parse(dateTimePattern));
            }
        }

        arguments = trimFromMatchOnwards(arguments, foundPatterns.get(foundPatterns.size() - 1));
        return foundDateTimes;
    }

    private List<String> findMatchingPatterns(String arguments, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arguments);

        List<String> matchingPatterns = new ArrayList<>();

        // find all substrings that match regex pattern
        while (matcher.find()) {
            matchingPatterns.add(matcher.group(1));
        }

        return matchingPatterns;
    }

    private String[] splitDoubleDateTimePattern(String doubleDateTimePattern) {
        if (doubleDateTimePattern.contains("to")) {
            return doubleDateTimePattern.split("\\s?to\\s?");
        } else {
            List<String> separatePatterns = findMatchingPatterns(doubleDateTimePattern, dateTimeRegex);
            return separatePatterns.toArray(String[]::new);
        }
    }

    private String trimFromMatchOnwards(String original, String pattern) {
        return original.replaceAll(pattern + ".*$", "").trim();
    }
}

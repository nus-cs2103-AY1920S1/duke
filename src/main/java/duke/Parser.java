package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Parser for {@link duke.command.Command} argument lists. It can convert an entire raw argument list into a multimap
 * between switch names and switch argument lists. Both required and optional switches are supported.
 */
public class Parser {
    protected Set<String> requiredSwitches = new TreeSet<>();
    protected Set<String> optionalSwitches = new TreeSet<>();

    /**
     * Registers the specified String as a switch while specifying if it is required or optional.
     * Commands missing required switches will cause an exception upon parsing.
     *
     * @param name        the String that uniquely identifies this switch.
     * @param isRequired  boolean specifying if the switch is required.
     */
    public void register(String name, boolean isRequired) {
        if(isRequired) requiredSwitches.add(name);
        else optionalSwitches.add(name);
    }

    /**
     * Parses the argument list into a multimap between switch names and switch argument lists. The command name is
     * taken as the first key of the multimap. Switches must have been registered using
     * {@link #register(String, boolean)} before parsing.
     *
     * @param args            the command argument list as a String[]
     * @return                a map with switch names as keys and switch argument lists as values
     * @throws DukeException  if there are repeated switches or if required switches are missing
     */
    public Map<String, String[]> parse(String[] args) throws DukeException {
        Set<String> requiredSwitchesRemaining = new TreeSet<>(requiredSwitches);
        Set<String> optionalSwitchesRemaining = new TreeSet<>(optionalSwitches);
        Map<String, String[]> switchArgs = new HashMap<>();

        int switchStartIndex = 0;
        for(int i = 1; i < args.length; i++) { // command name is an implied switch
            boolean isSwitch = false;
            if(requiredSwitches.contains(args[i])) {
                if(!requiredSwitchesRemaining.remove(args[i])) throw new DukeException("Repeated switch " + args[i]);
                isSwitch = true;
            }
            else if(optionalSwitches.contains(args[i])) {
                if(!optionalSwitchesRemaining.remove(args[i])) throw new DukeException("Repeated switch " + args[i]);
                isSwitch = true;
            }
            if(isSwitch) {
                try {
                    // This allows for switches with no arguments
                    switchArgs.put(args[switchStartIndex], Arrays.copyOfRange(args, switchStartIndex + 1, i));
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Bad arguments for switch " + args[switchStartIndex]);
                }
                switchStartIndex = i;
            }
        }
        // Add last switch
        switchArgs.put(args[switchStartIndex], Arrays.copyOfRange(args, switchStartIndex+1, args.length));

        if(!requiredSwitchesRemaining.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(String s : requiredSwitchesRemaining) {
                sb.append(s);
                sb.append(" ");
            }
            sb.append(requiredSwitchesRemaining.size() == 1 ? "is a required switch" : "are required switches");
            throw new DukeException(sb.toString());
        }
        return switchArgs;
    }

    /**
     * Returns a String formed by concatenating each String in the specified array in sequence while inserting a space
     * (<code>" "</code>) between each.
     *
     * @param strings  the String[] to concatenate
     * @return         the concatenated String
     */
    public static String concatenate(String[] strings) {
        return concatenate(strings, 0, strings.length);
    }

    /**
     * Returns a String formed by concatenating the Strings in the array within the specified range in sequence while
     * inserting a space (<code>" "</code>) between each.
     *
     * @param strings    the String[] containing the range to concatenate
     * @param fromIndex  the start index, inclusive
     * @param toIndex    the end index, exclusive
     * @return           the concatenated String
     */
    public static String concatenate(String[] strings, int fromIndex, int toIndex) {
        StringBuilder sb = new StringBuilder();
        for(int i = fromIndex; i < toIndex; i++) {
            sb.append(strings[i]);
            if(i != toIndex-1) sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Returns a LocalDate parsed from the specified String. Attempts to use several default {@link DateTimeFormatter}s
     * to parse the String. An exception is thrown if the input is not in a recognised format.
     * <p></p>
     * If the input contains both a date and a time, {@link #parseDateTime(String)} should be used instead.
     *
     * @param input           the String to be parsed. It must contain only a date and be in a recognised format.
     * @return                the LocalDate parsed from the input
     * @throws DukeException  if the String cannot be parsed
     */
    public static LocalDate parseDate(String input) throws DukeException {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ISO_DATE);
        formatters.add(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        formatters.add(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/YYYY"));

        LocalDate date = null;
        for(DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(input, formatter);
            } catch(DateTimeParseException e) {

            }
        }
        if(date == null) {
            throw new DukeException("Could not parse input as date");
        }
        return date;
    }

    /**
     * Returns a LocalDateTime parsed from the specified String. Attempts to use several default
     * {@link DateTimeFormatter}s to parse the String. An exception is thrown if the input is not in a
     * recognised format.
     * <p></p>
     * If the input contains only a date but not a time, {@link #parseDate(String)} should be used instead.
     *
     * @param input           the String to be parsed. It must contain both a date and a time
     *                        and be in a recognised format.
     * @return                the LocalDateTime parsed from the input
     * @throws DukeException  if the String cannot be parsed
     */
    public static LocalDateTime parseDateTime(String input) throws DukeException {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ISO_DATE_TIME);
        formatters.add(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
        formatters.add(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
        formatters.add(new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy HHmm")
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter());
        formatters.add(new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy HHmm")
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter());

        LocalDateTime dateTime = null;
        for(DateTimeFormatter formatter : formatters) {
            try {
                dateTime = LocalDateTime.parse(input, formatter);
                break;
            } catch(DateTimeParseException e) {

            }
        }
        if(dateTime == null) {
            throw new DukeException("Could not parse input as date");
        }
        return dateTime;
    }

    /**
     * Returns a String representing a verbosely formatted version of the specified date or datetime String. It first
     * attempts to parse the input as a {@link LocalDate}. If that fails, it tries to parse it as a
     * {@link LocalDateTime}. Upon successful parsing, it prettily formats the resulting object as a String.
     * <p></p>
     * LocalDates are formatted as <code>"d of MMMM yyyy"</code>.
     * <p>
     * LocalDateTimes are formatted as <code>"d of MMMM yyyy, h:mma"</code>.
     *
     * @param input           the String to be parsed. It can be either a date or datetime but must be in a
     *                        recognised format.
     * @return                String containing the prettily formatted date or datetime
     * @throws DukeException  if the input cannot be parsed as either a LocalDate or LocalDateTime
     */
    public static String parseDateOrDateTimeToString(String input) throws DukeException {
        String parsedString = null;
        try {
            parsedString = parseDate(input).format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy"));
        } catch(DukeException e1) {
            try {
                parsedString = parseDateTime(input).format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, h:mma"));
            } catch(DukeException e2) {

            }
        }
        if(parsedString == null) {
            throw new DukeException("Could not parse String as Date or DateTime");
        }
        return parsedString;
    }
}

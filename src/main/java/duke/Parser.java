package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.*;

public class Parser {
    protected Set<String> requiredSwitches = new TreeSet<>();
    protected Set<String> optionalSwitches = new TreeSet<>();
    public void register(String name, boolean required) {
        if(required) requiredSwitches.add(name);
        else optionalSwitches.add(name);
    }
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
    public static String concatenate(String[] strings) {
        return concatenate(strings, 0, strings.length);
    }
    public static String concatenate(String[] strings, int fromIndex, int toIndex) {
        StringBuilder sb = new StringBuilder();
        for(int i = fromIndex; i < toIndex; i++) {
            sb.append(strings[i]);
            if(i != toIndex-1) sb.append(" ");
        }
        return sb.toString();
    }
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

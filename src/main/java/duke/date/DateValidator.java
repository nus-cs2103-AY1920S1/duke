package duke.date;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Deals with making sense of user entered dates and times.
 */
public class DateValidator {

    private static final String INVALID_DATE_MSG = "Invalid date format! "
            + "Please ensure your date sticks to this format:\n"
            + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
            + "    Events : \"DD/MM/YYYY HHMM-HHMM\"";

    /**
     * Validates the date-time string entered by the user and returns an array with start, end times.
     * @param date Date-time input by the user.
     * @param hasEndTime Boolean indicating if the date-time has an end time.
     * @return Array of start time, along with end time for event.
     * @throws InvalidDateDukeException If the date format is invalid.
     */
    public LocalDateTime[] getAndValidateDates(String date, boolean hasEndTime) throws InvalidDateDukeException {
        ArrayList<String> dateParams = getDateParameters(date);
        if (isInvalidFormat(dateParams, hasEndTime)) {
            throw new InvalidDateDukeException(INVALID_DATE_MSG);
        }
        try {
            Month month = getMonth(dateParams);
            return getValidDate(dateParams, hasEndTime, month);
        } catch (DateTimeException e) {
            throw new InvalidDateDukeException(INVALID_DATE_MSG);
        }
    }

    private Month getMonth(ArrayList<String> dateParams) throws DateTimeException {
        return Month.of(Integer.parseInt(dateParams.get(1)));
    }

    private boolean isInvalidFormat(ArrayList<String> dateParams, boolean hasEndTime) {
        return isInvalidDeadlineFormat(dateParams, hasEndTime)
                || isInvalidEventFormat(dateParams, hasEndTime);
    }

    private boolean isInvalidEventFormat(ArrayList<String> dateParams, boolean hasEndTime) {
        return hasEndTime && dateParams.size() != 5;
    }

    private boolean isInvalidDeadlineFormat(ArrayList<String> dateParams, boolean hasEndTime) {
        return !hasEndTime && dateParams.size() != 4;
    }

    private ArrayList<String> getDateParameters(String date) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(date);
        ArrayList<String> dateParams = new ArrayList<>();
        while (m.find()) {
            dateParams.add(m.group());
        }
        return dateParams;
    }

    private LocalDateTime[] getValidDate(ArrayList<String> dateParams, boolean hasEndTime, Month month)
            throws InvalidDateDukeException {
        try {
            ArrayList<Integer> integerDateParams = getIntegerDateParams(dateParams);
            if (hasEndTime) {
                ArrayList<Integer> integerEndTimeParams = getEndTimeParams(dateParams);
                return getValidEventDates(integerDateParams, month, integerEndTimeParams);
            } else {
                return getValidDeadlineDate(integerDateParams, month);
            }
        } catch (NumberFormatException e) {
            throw new InvalidDateDukeException(INVALID_DATE_MSG);
        }
    }

    private ArrayList<Integer> getEndTimeParams(ArrayList<String> dateParams) {
        String end = dateParams.get(4);
        assert end.length() == 4 : "End time invalid for event!";
        int endHours = Integer.parseInt(end.substring(0, 2));
        int endMinutes = Integer.parseInt(end.substring(2));
        ArrayList<Integer> endParams = new ArrayList<>();
        endParams.add(endHours);
        endParams.add(endMinutes);
        return endParams;
    }

    private ArrayList<Integer> getIntegerDateParams(ArrayList<String> dateParams) throws InvalidDateDukeException {
        int day = Integer.parseInt(dateParams.get(0));
        int year = Integer.parseInt(dateParams.get(2));
        String start = dateParams.get(3);
        int startHours = Integer.parseInt(start.substring(0, 2));
        int startMinutes = Integer.parseInt(start.substring(2));
        return makeArrayList(day, year, startHours, startMinutes);
    }

    private ArrayList<Integer> makeArrayList(int... args) throws InvalidDateDukeException {
        ArrayList<Integer> dateParams = new ArrayList<>();
        int argCount = 0;
        for (int arg : args) {
            argCount++;
            dateParams.add(arg);
        }
        boolean invalidArgCount = argCount != 4;
        if (invalidArgCount) {
            throw new InvalidDateDukeException(INVALID_DATE_MSG);
        }
        return dateParams;
    }

    private LocalDateTime[] getValidDeadlineDate(ArrayList<Integer> intParams, Month month)
            throws InvalidDateDukeException {
        try {
            LocalDateTime dateTime = LocalDateTime.of(intParams.get(1),
                    month, intParams.get(0), intParams.get(2), intParams.get(3));
            return new LocalDateTime[] {dateTime};
        } catch (DateTimeException e) {
            throw new InvalidDateDukeException("Invalid date semantics!");
        }
    }

    private LocalDateTime[] getValidEventDates(ArrayList<Integer> intParams, Month month, ArrayList<Integer> endParams)
            throws InvalidDateDukeException {
        try {
            int year = intParams.get(1);
            int day = intParams.get(0);
            LocalDateTime dateTimeStart = LocalDateTime.of(year, month, day,
                    intParams.get(2), intParams.get(3));
            LocalDateTime dateTimeEnd = LocalDateTime.of(year, month, day,
                    endParams.get(0), endParams.get(1));
            if (areInvalidStartEndTimes(dateTimeStart, dateTimeEnd)) {
                throw new InvalidDateDukeException("Invalid date semantics!");
            }
            return new LocalDateTime[] {dateTimeStart, dateTimeEnd};
        } catch (DateTimeException e) {
            throw new InvalidDateDukeException("Invalid date semantics!");
        }
    }

    private boolean areInvalidStartEndTimes(LocalDateTime start, LocalDateTime end) {
        return start.isAfter(end) || start.isEqual(end);
    }
}

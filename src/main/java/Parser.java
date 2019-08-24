import java.util.ArrayList;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Parser {

    public static String parseDateTime(String dateTimeString) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HHmm");
            LocalDateTime dateAndTime = LocalDateTime.parse(dateTimeString, formatter);
            int day = dateAndTime.getDayOfMonth();
            String month = dateAndTime.getMonth().toString();
            int year = dateAndTime.getYear();
            int hour = dateAndTime.getHour();
            int minute = dateAndTime.getMinute();
            StringBuffer dateTime = new StringBuffer();
            dateTime.append(getIntegerOrdinal(day));
            dateTime.append(" of ");
            dateTime.append(month);
            dateTime.append(" ");
            dateTime.append(year);
            dateTime.append(", ");
            if (hour >= 12) {
                hour = hour - 12;
            }
            dateTime.append(hour);
            if (minute != 0) {
                dateTime.append(":");
                dateTime.append(minute);
            }
            if (hour < 12) {
                dateTime.append("am");
            } else {
                dateTime.append("pm");
            }
            return dateTime.toString();
        } catch (DateTimeParseException ex) {
            throw new DukeException(dateTimeString + " is not in dd/MM/yyyy HHmm format.");
        }
    }

    private static String getIntegerOrdinal(int integer) {
        int remainderHundred = integer % 100;
        if (remainderHundred > 9 && remainderHundred < 21) {
            return integer + "th";
        } else {
            int remainderTen = integer % 10;
            switch (remainderTen) {
                case 1:
                    return integer + "st";
                case 2:
                    return integer + "nd";
                case 3:
                    return integer + "rd";
                default:
                    return integer + "th";
            }
        }
    }

    public static Command parseAsCommand(String input) throws DukeException {
        input = input.trim();
        String[] split = input.split("\\s+");
        // was a command provided
        if (split[0].length() == 0) {
            throw new DukeMissingCommandException();
        }
        // is the command valid

        Command.Type type;

        switch (split[0]) {
            case "list":
                return new ShowListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
                type = Command.Type.ADD_TODO;
                break;
            case "event":
                type = Command.Type.ADD_EVENT;
                break;
            case "deadline":
                type = Command.Type.ADD_DEADLINE;
                break;
            case "delete":
                type = Command.Type.DELETE;
                break;
            case "done":
                type = Command.Type.COMPLETE;
                break;
            default:
                throw new DukeUnknownCommandException();
        }

        // if the command requires further parameters
        String[] parameters = new String[type.parametersExpected];
        boolean first = true;
        int parameterCount = 0;
        String nextDelim;
        Iterator<String> delims = type.delimiters.iterator();
        if (delims.hasNext()) {
            nextDelim = delims.next();
        } else {
            nextDelim = " ";
            // since split by whitespaces there will not be a word that is " "
        }
        StringBuffer currentParameter = new StringBuffer();

        for (int i = 1; i <= split.length; i++) {
            if (i == split.length || split[i].equals(nextDelim)) {
                String parameter = currentParameter.toString().trim();
                if (parameter.length() > 0) {
                    parameters[parameterCount] = parameter;
                } else {
                    parameters[parameterCount] = null;
                }
                if (i < split.length && split[i].equals(nextDelim)) {
                    if (delims.hasNext()) {
                        nextDelim = delims.next();
                    } else {
                        nextDelim = " ";
                    }
                }
                currentParameter = new StringBuffer();
                parameterCount++;
            } else {
                currentParameter.append(split[i]);
                currentParameter.append(" ");
            }
        }

        for (String param : parameters) {
            if (param == null) {
                throw new DukeMissingParameterException(type, parameters);
            }
        }

        switch (type) {
			case DELETE:
                return new DeleteTaskCommand(parameters[0]);
            case COMPLETE:
                return new CompleteTaskCommand(parameters[0]);
            case ADD_TODO:
            case ADD_DEADLINE:
            case ADD_EVENT:
                return new AddTaskCommand(type, parameters);
            default:
                return null; //unreachable
        }

    }
}

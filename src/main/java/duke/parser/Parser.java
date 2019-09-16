package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.filter.ComparisonOperator;
import duke.filter.IndexFilter;
import duke.filter.TimeFilter;
import duke.filter.TypeFilter;
import duke.task.TaskType;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * A class representing a parser.
 */
public class Parser {
    private String getFirstWord(String string) {
        int indexOfSpace = string.indexOf(' ');
        if (indexOfSpace == -1) {
            return string;
        }
        return string.substring(0, indexOfSpace);
    }

    private String removeFirstWord(String string) {
        int indexOfSpace = string.indexOf(' ');
        if (indexOfSpace == -1) {
            return "";
        }
        return string.substring(indexOfSpace + 1).stripLeading();
    }

    /**
     * Returns the command by parsing the command string that typed by the user.
     * @param commandString the command typed by the user
     * @return the command by parsing the command string that typed by the user.
     * @throws IllegalDescriptionException If the description of the task is illegal.
     * @throws IllegalCommandException If the command is illegal.
     */
    public Command parseCommand(String commandString)
            throws IllegalDescriptionException, IllegalCommandException, IllegalIndexOfTaskException {
        String stringWithoutLeadingSpaces = commandString.stripLeading();
        String commandType = getFirstWord(stringWithoutLeadingSpaces);
        String description = removeFirstWord(stringWithoutLeadingSpaces);
        switch (commandType) {
        //Ignore words after the exit or list command
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            return parseDoneCommand(description);
        case DeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(description);
        case ToDoCommand.COMMAND_WORD:
            return new ToDoCommand(description);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(description);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadlineTask(description);
        case EventCommand.COMMAND_WORD:
            return parseEventTask(description);
        default:
            throw new IllegalCommandException(
                    "I'm sorry, but I don't know what that means :-(");
        }
    }

    private DeadlineCommand parseDeadlineTask(String description) throws IllegalDescriptionException {
        int indexOfTime = description.indexOf("/by");
        if (indexOfTime == -1) {
            throw new IllegalDescriptionException(DeadlineCommand.getCommandHelpInfo());
        }
        assert indexOfTime <= description.length() && indexOfTime + 3 <= description.length() :
                        "String length: " + description.length() + " separator index: " + indexOfTime;
        String taskDescription = description.substring(0, indexOfTime).strip();
        String dateTime = description.substring(indexOfTime + 3).strip();

        return new DeadlineCommand(taskDescription, parseDateTime(dateTime));
    }

    private EventCommand parseEventTask(String description) throws IllegalDescriptionException {
        int indexOfTime = description.indexOf("/at");
        if (indexOfTime == -1) {
            throw new IllegalDescriptionException(DeadlineCommand.getCommandHelpInfo());
        }
        assert indexOfTime <= description.length() && indexOfTime + 3 <= description.length() :
                        "String length: " + description.length() + " separator index: " + indexOfTime;
        String taskDescription = description.substring(0, indexOfTime).strip();
        String dateTime = description.substring(indexOfTime + 3).strip();

        return new EventCommand(taskDescription, parseDateTime(dateTime));
    }

    private LocalDate parseDate(String dateString) throws IllegalDescriptionException {
        //If no date provided, return today's date.
        if (dateString.isEmpty()) {
            return LocalDate.now();
        }
        String[] dayMonthYear = dateString.split("/");
        if (dayMonthYear.length != 3) {
            throw new IllegalDescriptionException("The format of date should be DD/MM/YYYY.");
        }
        try {
            LocalDate date = LocalDate.of(Integer.parseInt(dayMonthYear[2]),
                    Integer.parseInt(dayMonthYear[1]),
                    Integer.parseInt(dayMonthYear[0]));
            return date;
        } catch (NumberFormatException | DateTimeException e){
           throw new IllegalDateException("Please provide a valid date.");
        }
    }

    private LocalTime parseTime(String timeString) throws IllegalDescriptionException {
        if (timeString.isEmpty()) {
            throw new IllegalDescriptionException("Time cannot be empty.");
        }
        String[] hourMinute = timeString.split(":");
        if (hourMinute.length != 2) {
            throw new IllegalDescriptionException("The format of time should be HH:MM.");
        }
        try {
            LocalTime time = LocalTime.of(
                    Integer.parseInt(hourMinute[0]),
                    Integer.parseInt(hourMinute[1]));
            return time;
        } catch (NumberFormatException | DateTimeException e) {
            throw new IllegalTimeException("Please provide a valid time");
        }
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws IllegalDescriptionException {
        String dateString = getFirstWord(dateTimeString);
        String timeString = removeFirstWord(dateTimeString);
        if(timeString.isEmpty()) {
            timeString = dateTimeString;
            return LocalDateTime.of(parseDate(""), parseTime(timeString));
        }
        LocalDate date = parseDate(dateString);
        LocalTime time = parseTime(timeString);
        return LocalDateTime.of(date, time);
    }

    private IntStream parseRange(String description) throws IllegalIndexOfTaskException {
        int indexOfOpenBracket = description.indexOf('[');
        int indexOfColon = description.indexOf(':');
        int indexOfCloseBracket = description.indexOf(']');
        String start = description.substring(indexOfOpenBracket + 1, indexOfColon).strip();
        String end = description.substring(indexOfColon + 1, indexOfCloseBracket).strip();
        try {
            int startingIndex = 0;
            if (!start.isEmpty()) {
                startingIndex = Integer.parseInt(start);
            }
            if (end.isEmpty()) {
                return IntStream.iterate(startingIndex, index -> index + 1);
            } else {
                int endingIndex = Integer.parseInt(end);
                return IntStream.iterate(startingIndex, index -> index < endingIndex, index -> index + 1);
            }
        } catch (NumberFormatException e) {
            throw new IllegalIndexOfTaskException("Please provide valid indices for the range");
        }
    }

    private IntStream parseIndices(String description) {
        ArrayList<Integer> indices = new ArrayList<>();
        while (!description.isEmpty()) {
            try {
                indices.add(Integer.parseInt(getFirstWord(description)));
            } catch (NumberFormatException e) {
                //Ignore words that is not an integer
            }
            description = removeFirstWord(description);
        }
        return indices.stream().mapToInt(Integer::intValue).sorted().distinct();
    }

    private IndexFilter getIndexFilter(String description) throws IllegalIndexOfTaskException {
        if (description.matches("\\[.*:.*\\]")) {
            return new IndexFilter(parseRange(description));
        } else {
            return new IndexFilter(parseIndices(description));
        }
    }

    private TaskType parseTaskType(String taskType) throws IllegalDescriptionException {
        switch (taskType) {
        case "todo":
            return TaskType.ToDo;
        case "event":
            return TaskType.Event;
        case "deadline":
            return TaskType.Deadline;
        default:
            throw new IllegalDescriptionException("Please provide a valid task type");
        }
    }

    private TypeFilter getTypeFilter(String description) throws IllegalDescriptionException {
        return new TypeFilter(parseTaskType(removeFirstWord(description)));
    }

    private TimeFilter getTimeFilter(String description) throws IllegalDescriptionException {
        String preposition = getFirstWord(description);
        LocalDateTime dateTime = parseDateTime(removeFirstWord(description));

        switch (preposition) {
        case "/on":
            return new TimeFilter(ComparisonOperator.EqualTo, dateTime);
        case "/before":
            return new TimeFilter(ComparisonOperator.LessThan, dateTime);
        case "/after":
            return new TimeFilter(ComparisonOperator.GreaterThan, dateTime);
        case "/from":
            return new TimeFilter(ComparisonOperator.GreaterThanOrEqualTo, dateTime);
        case "/until":
            return new TimeFilter(ComparisonOperator.LessThanOrEqualTo, dateTime);
        default:
            throw new IllegalDescriptionException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private DeleteCommand parseDeleteCommand(String description) throws
            IllegalDescriptionException, IllegalIndexOfTaskException {
        String filterType = getFirstWord(description);
        switch (filterType) {
        case "/type":
            return new DeleteCommand(getTypeFilter(description));
        case "/on":
        case "/before":
        case "/after":
        case "/from":
        case "/until":
            return new DeleteCommand(getTimeFilter(description));
        default:
            return new DeleteCommand(getIndexFilter(description));
        }
    }

    private DoneCommand parseDoneCommand(String description) throws
            IllegalDescriptionException, IllegalIndexOfTaskException {
        String filterType = getFirstWord(description);
        switch (filterType) {
        case "/type":
            return new DoneCommand(getTypeFilter(description));
        case "/on":
        case "/before":
        case "/after":
        case "/from":
        case "/until":
            return new DoneCommand(getTimeFilter(description));
        default:
            return new DoneCommand(getIndexFilter(description));
        }
    }
}
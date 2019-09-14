package duke.core;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;
import duke.commands.NullCommand;
import duke.commands.AddToDoCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;

import java.util.List;
import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Parser {

    private static String[] responses = new String[]{"/by","/at"};

    public static Command parse(String input) throws DukeException, IllegalArgumentException {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("bye")) {
            return new ExitCommand();
        } else if (tokens[0].equals("list")) {
            return new ListCommand();
        }
        checkValidLength(tokens);

        if (tokens[0].equals("done")) {
            return DoneCommand.createDoneIfValid(tokens);
        } else if (tokens[0].equals("delete")) {
            return DeleteCommand.createDeleteIfValid(tokens);
        } else {
            return Parser.createAddCommandIfValid(tokens);
        }
    }

    public static void checkValidLength(String[] tokens) throws IllegalArgumentException {
        List<String> group1 = List.of("todo", "deadline", "event");
        List<String> group2 = List.of("done", "delete");
        if (tokens.length == 1 && group1.contains(tokens[0])) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! The description of a %s cannot be empty.",tokens[0]));
        } else if (tokens.length == 1 && group2.contains(tokens[0])) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! %s command requires integer.",tokens[0]));
        }
    }


    public static Command createAddCommandIfValid(String[] tokens) throws DukeException, IllegalArgumentException {
        List<String> validCommands = List.of("todo", "deadline", "event");

        if (!validCommands.contains(tokens[0])) {
            throw new DukeException("Command doesn't exist", DukeExceptionType.INVALIDCOMMAND);
        }
        if (tokens[0].equals("todo")) {
            return new AddToDoCommand(tokens);
        } else if (tokens[0].equals("deadline")) {
            return createDateCommandIfValid(tokens,0);
        } else if (tokens[0].equals("event")) {
            return createDateCommandIfValid(tokens,1);
        } else {
            return new NullCommand();
        }
    }


    public static String parseDateTime(String dateTimeString) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateAndTime = LocalDateTime.parse(dateTimeString, formatter);


            int year = dateAndTime.getYear();
            String month = dateAndTime.getMonth().toString();
            int day = dateAndTime.getDayOfMonth();
            int hour = dateAndTime.getHour();
            int minute = dateAndTime.getMinute();

            StringBuffer dateTime = new StringBuffer();

            dateTime.append(getIntegerOrdinal(day));
            dateTime.append(" of ");
            dateTime.append(month);
            dateTime.append(" ");
            dateTime.append(year);
            dateTime.append(", ");
            dateTime.append((hour > 12 ? hour - 12 : hour == 0 ? 12 : hour));
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
        } catch (DateTimeParseException exception) {
            //put it as general mistake first
            throw new DukeException(dateTimeString + " is not in valid dd/MM/yyyy HHmm format.",
                    DukeExceptionType.GENERALMISTAKE);
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


    public static Command createDateCommandIfValid(String [] tokens, int mode) throws DukeException {
        List<String> lst = Arrays.asList(tokens);
        String key = responses[mode];
        if (!lst.contains(key)) {
            throw new IllegalArgumentException("Missing deadline");
        }

        int index = lst.indexOf(key);
        int argLength = tokens.length - index - 1;

        if (argLength == 2) {
            String dateTimeString = tokens[index+1] + " " + tokens[index+2];
            String correctDate = Parser.parseDateTime(dateTimeString);
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < index ; i++) {
                String curr = tokens[i];
                builder.append(curr);
                if (i==index-1) {
                    break;
                }
                builder.append(" ");
            }
            return createDateCommand(mode,builder,correctDate);
        } else {
            throw new DukeException("Please input a date in dd/MM/yyyy HHmm format.",
                    DukeExceptionType.GENERALMISTAKE);
        }

    }

    public static Command createDateCommand(int mode,StringBuilder builder, String correctDate) {
        if (mode==0) {
            return new AddDeadlineCommand(builder.toString(),correctDate);
        } else {
            return new AddEventCommand(builder.toString(),correctDate);
        }
    }



}







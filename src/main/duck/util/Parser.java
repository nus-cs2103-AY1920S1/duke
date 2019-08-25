package duck.util;

import duck.command.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    public static Command parseCommand(String info) throws DukeException{

        String[] infos = info.trim().split("\\s+", 2);
        if(infos[0].equals("list")) {
            return new ListCommand();
        } else if (infos[0].equals("bye")) {
            return new ExitCommand();
            //the rest requires at least two arguments
        } else {
            if (infos.length <2) {
                throw new DukeException("☹ OOPS!!! No enough information entered");
            }
            switch (infos[0]) {
            case "done":
                try {
                    int order = Integer.valueOf(infos[1]);
                    return new DoneCommand(order - 1);
                } catch (NumberFormatException e) {
                    throw new DukeException("☹ OOPS!!! The task number is not a number!");
                }
            case "delete":
                try {
                    int order = Integer.valueOf(infos[1]);
                    return new DeleteCommand(order - 1);
                } catch (NumberFormatException e) {
                    throw new DukeException("☹ OOPS!!! The task number is not a number!");
                }
            case "todo":
                return new AddCommand("todo", infos[1]);
            case "deadline":
                if (!infos[1].contains(" /by ")) {
                    throw new DukeException ("☹ OOPS!!! The description of a deadline is not enough.");
                }
                try {
                    String[] details = infos[1].split(" /by ");
                    LocalDateTime due = Parser.parseDateTime(details[1]);
                    return new AddCommand("deadline", details[0], due);
                } catch (DateTimeParseException e) {
                    throw new DukeException("The deadline is in invalid format");
                }
            case "event":
                if (!infos[1].contains(" /at ")) {
                    throw new DukeException ("☹ OOPS!!! The description of a event is not enough.");
                }
                try {
                    String[] details = infos[1].split(" /at ");
                    String[] times = details[1].split("-");
                    LocalDateTime startDateTime = Parser.parseDateTime(times[0]);
                    LocalTime time = LocalTime.parse(times[1]);
                    return new AddCommand("event", details[0], startDateTime, time);
                } catch (DateTimeParseException e) {
                    throw new DukeException("The duration is in invalid format");
                }
            }
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static LocalDateTime parseDateTime(String info) throws DateTimeParseException {
        return LocalDateTime.parse(info,formatter);
    }
}

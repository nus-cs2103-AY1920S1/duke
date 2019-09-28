package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a class that parsers user's commands(strings) to <code>LocalDateTime</code> objects and executable
 * <code>Command</code> objects.
 */
public class Parser {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    /**
     * Parses a given string in a certain format to a <code>LocalDateTime</code> object. The string must be in the
     * format of "day/month/year hh:mm" to be possibly parsed, for example: "31/05/2000 15:00".
     *
     * @param info a string to be parsed into a <code>LocalDateTime</code> object
     * @return a <code>LocalDateTime</code> object corresponds to the  date time information
     * @throws DateTimeParseException If the entered string is not in the format of "day/month/year hh:mm"
     */
    public static LocalDateTime parseDateTime(String info) throws DateTimeParseException {
        return LocalDateTime.parse(info, formatter);
    }

    /**
     * Parses the user's commands, which are plain strings, to <code>Command</code> objects. White spaces are allowed
     * for between and around commands and following details. They are generally ignores but will be included in the
     * description for <code>Task</code> objects. Except <code>ListCommand</code> and
     * <code>ExitCommand</code>, other commands require addition details. For <code>DoneCommand</code> and
     * <code>DeleteCommand</code>, a number about the target task should be specified. For <code>AddCommand</code>,
     * depends on the type of the task, additional information should be provided in a certain format: (1) In order to
     * add <code>Todo</code> task, description part is needed; (2) In order to add <code>Deadline</code> task,
     * description and due date time are needed, and these two parts are separated by "/by"; (3) In order to add
     * <code>Event</code> task, description and duration are needed, and these two parts are separated by "/at".
     *
     * @param info a string containing the type of <code>Command</code> and additional details
     * @return a <code>Command</code> object that obtains necessary details to execute
     * @throws DukeException If the given string cannot fit in existing commands, or lacks necessary details, or
     *                       provides date and time in invalid format
     */
    public static Command parseCommand(String info) throws DukeException {

        String[] infos = info.trim().split("\\s+", 2);
        String primaryCommand = infos[0];
        switch (primaryCommand) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "done":
            if (infos.length < 2) {
                throw new DukeException("☹ OOPS!!! No enough information entered");
            }
            try {
                int order = Integer.valueOf(infos[1]);
                return new DoneCommand(order - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! The task number is not a number!");
            }
        case "delete":
            if (infos.length < 2) {
                throw new DukeException("☹ OOPS!!! No enough information entered");
            }
            try {
                int order = Integer.valueOf(infos[1]);
                return new DeleteCommand(order - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! The task number is not a number!");
            }
        case "todo":
            if (infos.length < 2) {
                throw new DukeException("☹ OOPS!!! No enough information entered");
            }
            return new AddCommand(new Todo(infos[1]));
        case "deadline":
            if (infos.length < 2) {
                throw new DukeException("☹ OOPS!!! No enough information entered");
            }
            if (!infos[1].contains(" /by ")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline is not enough.");
            }
            try {
                String[] details = infos[1].split(" /by ");
                LocalDateTime due = Parser.parseDateTime(details[1]);
                return new AddCommand(new Deadline(details[0], due));
            } catch (DateTimeParseException e) {
                throw new DukeException("The deadline is in invalid format");
            }
        case "event":
            if (infos.length < 2) {
                throw new DukeException("☹ OOPS!!! No enough information entered");
            }
            if (!infos[1].contains(" /at ")) {
                throw new DukeException("☹ OOPS!!! The description of a event is not enough.");
            }
            try {
                String[] details = infos[1].split(" /at ");
                String[] times = details[1].split("-");
                LocalDateTime startDateTime = Parser.parseDateTime(times[0]);
                LocalTime time = LocalTime.parse(times[1]);
                return new AddCommand(new Event(details[0], startDateTime, time));
            } catch (DateTimeParseException e) {
                throw new DukeException("The duration is in invalid format");
            }
        case "find":
            return new FindCommand(infos[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }


}

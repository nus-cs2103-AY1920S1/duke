package duke.core;

import duke.commands.AddCommand;
import duke.commands.ArchiveCommand;
import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EmptyCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.RetrieveCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.LocalDateTime;

public class Parser {
    /**
     * Parse input String and return corresponding LocalDateTime instance.
     * @param dateTime String input
     * @throws DukeException Duke exception
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            LocalDateTime parsedDateTime;
            try {
                parsedDateTime = LocalDateTime.parse(dateTime);
            } catch (Exception e) {
                throw new DukeException("Deadline should be given in \"<DD/MM/YYYY> <HHMM>\" format");
            } finally {
                String[] splitDateTime = dateTime.split(" ");
                String[] splitDate = splitDateTime[0].split("\\/");
                parsedDateTime = LocalDateTime.of(
                        Integer.parseInt(splitDate[2]),
                        Integer.parseInt(splitDate[1]),
                        Integer.parseInt(splitDate[0]),
                        Integer.parseInt(splitDateTime[1]) / 100,
                        Integer.parseInt(splitDateTime[1]) % 100);

                return parsedDateTime;

            }
        } catch (Exception e) {
            throw new DukeException("Deadline should be given in \"<DD/MM/YYYY> <HHMM>\" format");
        }
    }

    /**
     * Parse input String and return corresponding command.
     * @param command Command
     * @throws DukeException Duke exception
     */
    public static Command parseCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "archive":
            if (splitCommand.length == 1 || splitCommand[1].trim().equals("")) {
                throw new DukeException("usage: archive <archive_name>");
            }
            return new ArchiveCommand(splitCommand[1]);
        case "retrieve":
            if (splitCommand.length == 1) {
                throw new DukeException("usage: retrieve <archive_name>");
            }
            return new RetrieveCommand(splitCommand[1]);
        case "list":
            return new ListCommand();
        case "clear":
            return new ClearCommand();
        case "empty":
            return new EmptyCommand();
        case "find":
            if (splitCommand.length == 1 || splitCommand[1].trim().equals("")) {
                throw new DukeException("usage: find <pattern>");
            }
            return new FindCommand(splitCommand[1].trim());
        case "done":
            int doneIndex;
            if (splitCommand.length == 1) {
                throw new DukeException("usage: done <task_number>");
            }
            try {
                doneIndex = Integer.parseInt(splitCommand[1].trim());
            } catch (NumberFormatException e) {
                throw new DukeException("usage: done <task_number>");
            }
            return new DoneCommand(doneIndex);
        case "delete":
            int deleteIndex;
            if (splitCommand.length == 1) {
                throw new DukeException("usage: delete <task_number>");
            }
            try {
                deleteIndex = Integer.parseInt(splitCommand[1].trim());
            } catch (NumberFormatException e) {
                throw new DukeException("usage: delete <task_number>");
            }
            return new DeleteCommand(deleteIndex);
        case "todo":
            if (splitCommand.length == 1 || splitCommand[1].trim().equals("")) {
                throw new DukeException("usage: todo <todo_task>");
            }
            return new AddCommand(new Todo(splitCommand[1].trim()));
        case "deadline":
            if (splitCommand.length == 1) {
                throw new DukeException("usage: deadline <deadline_task> /by <deadline>");
            }
            String[] parsedDeadline = splitCommand[1].split(" \\/by ");
            if (parsedDeadline.length == 1) {
                throw new DukeException("usage: deadline <deadline_task> /by <deadline>");
            }
            return new AddCommand(new Deadline(parsedDeadline[0], Parser.parseDateTime(parsedDeadline[1].trim())));
        case "event":
            if (splitCommand.length == 1) {
                throw new DukeException("usage: event <event_name> /at <event_location>");
            }
            String[] parsedEvent = splitCommand[1].split(" \\/at ");
            if (parsedEvent.length == 1) {
                throw new DukeException("usage: event <event_name> /at <event_location>");
            }
            return new AddCommand(new Event(parsedEvent[0], Parser.parseDateTime(parsedEvent[1].trim())));
        default:
            return new InvalidCommand();
        }
    }
}

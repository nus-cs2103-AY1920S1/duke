import java.time.LocalDateTime;

public class Parser {
    /***
     * Parse input String and return corresponding LocalDateTime instance.
     * @param dateTime String input
     * @throws DukeException
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            LocalDateTime parsedDateTime;
            try {
                parsedDateTime = LocalDateTime.parse(dateTime);
            } catch (Exception e) {
                throw new DukeException("Invalid DateTime!");
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
            throw new DukeException("Invalid DateTime!");
        }
    }

    /***
     * Parse input String and return corresponding command.
     * @param command
     * @throws DukeException
     */
    public static Command parseCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(splitCommand[1].trim());
        case "done":
            return new DoneCommand(Integer.parseInt(splitCommand[1].trim()));
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitCommand[1].trim()));
        case "todo":
            if (splitCommand.length == 1)
                throw new DukeException("The description of a deadline cannot be empty.");
            return new AddCommand(new Todo(splitCommand[1].trim()));
        case "deadline":
            if (splitCommand.length == 1)
                throw new DukeException("The description of a deadline cannot be empty.");
            String[] parsedDeadline = splitCommand[1].split(" \\/by ");
            if (parsedDeadline.length == 1)
                throw new DukeException("Deadline is missing a deadline");
            return new AddCommand(new Deadline(parsedDeadline[0], Parser.parseDateTime(parsedDeadline[1].trim())));
        case "event":
            if (splitCommand.length == 1)
                throw new DukeException("The description of an event cannot be empty.");
            String[] parsedEvent = splitCommand[1].split(" \\/at ");
            if (parsedEvent.length == 1)
                throw new DukeException("Event is missing a location");
            return new AddCommand(new Event(parsedEvent[0], Parser.parseDateTime(parsedEvent[1].trim())));
        default:
            return new InvalidCommand();
        }
    }
}

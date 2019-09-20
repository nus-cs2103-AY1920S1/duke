package seedu.duke;

import java.util.Optional;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ToDo;

public class Parser {
    /**
     * Takes in a line and returns a Optional Task. The Optional task is empty if
     * the line is malformed.
     * 
     * @param line the line from text file to be parsed into task.
     * @return an Optional Task object parsed from the input line
     */
    public static Optional<Task> parseLineToTask(String line) {
        String[] tokens = Utils.trimAll(line.split("\\|"));
        if (line.length() < 1) {
            return Optional.empty();
        }

        try {
            switch (tokens[0]) {
            case "E":
                return Optional.of(Event.fromFormattedString(tokens));
            case "D":
                return Optional.of(Deadline.fromFormattedString(tokens));
            case "T":
                return Optional.of(ToDo.fromFormattedString(tokens));
            default:
                return Optional.empty();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Given a command string, return a command object.
     * 
     * @param fullCommand a String containing the full command
     * @return Command a command object
     * @throws DukeException if any error occurs parsing the command
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] commandTokens = Utils.trimAll(fullCommand.split(" ", 2));
            switch (commandTokens[0]) {
            case "todo":
            case "deadline":
            case "event":
                return parseAddCommand(fullCommand);
            case "delete":
                return parseDeleteCommand(commandTokens);
            case "list":
                return new ListCommand();
            case "done":
                return parseDoneCommand(commandTokens);
            case "bye":
            case "exit":
                return new ExitCommand();
            default:
                throw new DukeException("No such command!");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    // something else: help message
    // done <number>
    // todo <desc>
    // deadline <desc> /by <time>
    // event desc /at <time>

    private static AddCommand parseAddCommand(String fullCommand) throws DukeException {
        String[] commandTokens = fullCommand.split(" ", 2);
        switch (commandTokens[0]) {
        case "todo":
            return parseNewTodoCommand(fullCommand);
        case "deadline":
            return parseNewDeadlineCommand(fullCommand);
        case "event":
            return parseNewEventCommand(fullCommand);
        default:
            throw new DukeException("Neither todo, event nor deadline.");
        }
    }

    // event desc /at <time>
    private static AddCommand parseNewEventCommand(String fullCommand) throws DukeException {
        String descAtTime = fullCommand.split(" ", 2)[1];
        String[] descTime = Utils.trimAll(descAtTime.split(" */?at *"));
        if (descTime.length == 1) {
            throw new DukeException("An event must have a '/at' delimiter separating " + "description and time!\n"
                    + "Proper format: 'event desc /at time");
        } else if (descTime[0].equals("")) {
            throw new DukeException("Description missing!");
        }
        return new AddCommand(new Event(descTime[0], descTime[1]));
    }

    // deadline <desc> /by <time>
    private static AddCommand parseNewDeadlineCommand(String fullCommand) throws DukeException {
        String descByTime = fullCommand.split(" ", 2)[1];
        String[] descTime = Utils.trimAll(descByTime.split(" */?by *"));
        if (descTime.length == 1) {
            throw new DukeException("A deadline must have a '/by' delimiter separating " + "description and time!\n"
                    + "Proper format: 'event desc /at time");
        } else if (descTime[0].equals("")) {
            throw new DukeException("Description missing!");
        }
        return new AddCommand(new Deadline(descTime[0], descTime[1]));
    }

    private static boolean todoMissingDescription(String[] commandTokens) {
        return commandTokens.length != 2 || commandTokens[1].equals("");
    }

    // todo <desc>
    private static AddCommand parseNewTodoCommand(String fullCommand) throws DukeException {
        String[] commandTokens = fullCommand.split(" ", 2);
        if (todoMissingDescription(commandTokens)) {
            throw new DukeException("A todo must have a non-empty description!");
        }
        return new AddCommand(new ToDo(commandTokens[1]));
    }

    // (probably) delete number
    private static DeleteCommand parseDeleteCommand(String[] commandTokens) {
        Integer number = Integer.parseInt(commandTokens[1].trim());
        return new DeleteCommand(number);
    }

    private static DoneCommand parseDoneCommand(String[] commandTokens) {
        return new DoneCommand(Integer.parseInt(commandTokens[1]));
    }
}

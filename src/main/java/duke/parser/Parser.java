package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Reads commands give to Duke.
     * Creates the appropriate command object.
     *
     * @param fullCommand Whole command where the first word will be extracted.
     * @return Command object that is created according to the type of command.
     */
    public static Command parse(String fullCommand) {
        String[] currArray = fullCommand.split("\\s+", 2);
        switch (currArray[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
        case "l":
            return new ListCommand();
        case "complete":
        case "c":
            return CompleteCommand.process(currArray);
        case "remove":
        case "r":
            return RemoveCommand.process(currArray);
        case "find":
        case "f":
            return FindCommand.process(currArray);
        case "todo":
        case "t":
            return new AddCommand(Todo.process(currArray));
        case "deadline":
        case "d":
            return new AddCommand(Deadline.process(currArray));
        case "event":
        case "e":
            return new AddCommand(Event.process(currArray));
        case "help":
        case "h":
            return new HelpCommand();
        case "update":
        case "u":
            return UpdateCommand.process(currArray);
        case "schedule":
            return ScheduleCommand.process(currArray);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
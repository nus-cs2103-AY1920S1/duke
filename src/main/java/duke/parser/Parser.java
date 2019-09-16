package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ScheduleCommand;
import duke.command.UndoCommand;
import duke.command.UpdateCommand;
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
        String[] currArray = (fullCommand.trim()).split("\\s+", 2);
        switch (currArray[0]) {
        case "bye":
        case "b":
            return new ExitCommand();
        case "list":
        case "l":
            return new ListCommand();
        case "complete":
        case "done":
        case "c":
            return CompleteCommand.process(currArray);
        case "delete":
        case "d":
            return DeleteCommand.process(currArray);
        case "find":
        case "f":
            return FindCommand.process(currArray);
        case "todo":
        case "-t":
            return new AddCommand(Todo.process(currArray));
        case "deadline":
        case "-d":
            return new AddCommand(Deadline.process(currArray));
        case "event":
        case "-e":
            return new AddCommand(Event.process(currArray));
        case "help":
        case "h":
            return new HelpCommand();
        case "update":
            return UpdateCommand.process(currArray);
        case "schedule":
        case "s":
            return ScheduleCommand.process(currArray);
        case "undo":
        case "u":
            return new UndoCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
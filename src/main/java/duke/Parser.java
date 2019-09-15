package duke;

import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.Command;

import duke.exception.DukeException;
import duke.exception.EmptyDateTimeDukeException;
import duke.exception.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {

    /**
     * Parses user input string and returns requested command.
     * @param fullCommand expects specific format for string
     * @return child class of Command object, corresponding to parsed string
     * @throws DukeException if command is not of expected format
     */
    public static Command parse(String fullCommand) throws DukeException {
        String s = fullCommand.trim();
        String[] input = s.split(" ");
        String description;
        String date;
        String number;

        int num;
        switch (input[0]) {
        case "todo":
            description = s.substring(s.indexOf(" ") + 1);
            return new AddCommand(new ToDo(description));
        case "event":
            try {
                description = s.substring(s.indexOf(" ") + 1, s.indexOf("/") - 1);
                date = s.substring((s.indexOf("/") + 4));
            } catch (StringIndexOutOfBoundsException err) {
                throw new EmptyDateTimeDukeException();
            }
            return new AddCommand(new Event(description, date));
        case "deadline":
            try {
                description = s.substring(s.indexOf(" ") + 1, s.indexOf("/") - 1);
                date = s.substring((s.indexOf("/") + 4));
            } catch (StringIndexOutOfBoundsException err) {
                throw new EmptyDateTimeDukeException();
            }
            return new AddCommand(new Deadline(description, date));
        case "delete":
            number = s.substring(s.indexOf(" ") + 1);
            num = Integer.parseInt(number.trim());
            return new DeleteCommand(num);
        case "done":
            number = s.substring(s.indexOf(" ") + 1);
            num = Integer.parseInt(number.trim());
            return new DoneCommand(num);
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(s.substring(5));
        case "bye":
            return new ExitCommand();
        default:
            throw new UnknownInputException();
        }
    }
}

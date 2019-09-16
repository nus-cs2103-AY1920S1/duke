package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyDateTimeDukeException;
import duke.exception.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.util.Arrays;
import java.util.List;

public class Parser {

    /**
     * Parses user input string and returns requested command.
     * @param fullCommand expects specific format for string
     * @return child class of Command object, corresponding to parsed string
     * @throws DukeException if command is not of expected format
     */
    public static Command parse(String fullCommand) throws DukeException {
        assert fullCommand != null;

        String s = fullCommand.trim();
        String[] input = s.split(" ");
        String description;
        String date;
        String number;
        String tagName = null;
        List<String> list = Arrays.asList(input);

        if (list.contains("/t")) {
            tagName = list.get(list.indexOf("/t") + 1);
        }

        int num;
        switch (input[0]) {
        case "todo":
            if (tagName == null) {
                description = s.substring(s.indexOf(" ") + 1);
                return new AddCommand(new ToDo(description));
            } else {
                description = s.substring(s.indexOf(" ") + 1, s.indexOf("/t"));
                return new AddCommand(new ToDo(description, tagName));
            }
        case "event":
            try {
                description = s.substring(s.indexOf(" ") + 1, s.indexOf("/") - 1);
                if (tagName == null) {
                    date = s.substring((s.indexOf("/") + 4));
                    return new AddCommand(new Event(description, date));
                } else {
                    date = s.substring((s.indexOf("/") + 4), s.indexOf("/t"));
                    return new AddCommand(new Event(description, date, tagName));
                }
            } catch (StringIndexOutOfBoundsException err) {
                throw new EmptyDateTimeDukeException();
            }
        case "deadline":
            try {
                description = s.substring(s.indexOf(" ") + 1, s.indexOf("/") - 1);
                if (tagName == null) {
                    date = s.substring((s.indexOf("/") + 4));
                    return new AddCommand(new Deadline(description, date));
                } else {
                    date = s.substring((s.indexOf("/") + 4), s.indexOf("/t"));
                    return new AddCommand(new Deadline(description, date, tagName));
                }
            } catch (StringIndexOutOfBoundsException err) {
                throw new EmptyDateTimeDukeException();
            }
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

package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyDateTimeDukeException;
import duke.exception.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {

    /**
     * (Review:
     * @param fullCommand
     * @return object of a particular childClass of Command
     */
    public static Command parse(String fullCommand) throws DukeException {
        String s = fullCommand.trim();
        String[] input = s.split(" ");
        String description, date, number;

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
        case "bye":
            return new ExitCommand();
        default:
            throw new UnknownInputException();
        }
    }
}

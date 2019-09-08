package duke;

import duke.command.AliasCommand;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.PlaceCommand;
import duke.exception.DukeException;
import duke.exception.EmptyFieldDukeException;
import duke.exception.InvalidCommandDukeException;
import duke.exception.PlaceParseDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    /**
     * Looks at first token, determine & construct duke.command.Command sub-classes as per parsed.
     *
     * @param str Expects string to be parse as duke.command.Command.
     * @return duke.command.Command object as per parsed.
     * @throws DukeException if command can't be parsed.
     */
    public static Command parse(String str) throws DukeException {
        assert str != null;

        str = str.trim();
        int lastIndex = str.indexOf(' ');
        if (lastIndex < 0) {
            lastIndex = str.length();
        }
        String[] temp;
        switch (str.substring(0, lastIndex)) {
        case "bye":
            return new ExitCommand();
        case "delete":
            return new DeleteCommand(str.substring(lastIndex));
        case "done":
            return new DoneCommand(str.substring(lastIndex));
        case "find":
            return new FindCommand(str.substring(lastIndex));
        case "list":
            return new ListCommand();
        case "deadline":
            temp = str.substring(8)
                .split(" /by ");
            if (temp.length < 1 || temp[0].isBlank()) {
                throw new EmptyFieldDukeException("description", "deadline");
            }
            if (temp.length < 2 || temp[1].isBlank()) {
                throw new EmptyFieldDukeException("time", "deadline");
            }
            return new AddCommand(new Deadline(temp[0], temp[1]));
        case "event":
            temp = str.substring(5)
                .split(" /at ");
            if (temp.length < 1 || temp[0].isBlank()) {
                throw new EmptyFieldDukeException("description", "event");
            }
            if (temp.length < 2 || temp[1].isBlank()) {
                throw new EmptyFieldDukeException("time", "event");
            }
            return new AddCommand(new Event(temp[0], temp[1]));
        case "todo":
            return new AddCommand(new Todo(str.substring(lastIndex)));
        case "place":
            temp = str.substring(lastIndex).trim().split("\\s+");
            try {
                double latitude = Double.parseDouble(temp[1]);
                double longitude = Double.parseDouble(temp[2]);
                return new PlaceCommand(temp[0], latitude, longitude);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                throw new EmptyFieldDukeException("latitude & longitude", "place");
            } catch (NumberFormatException e) {
                throw new PlaceParseDukeException();
            }
        case "alias":
            return new AliasCommand(str.substring(lastIndex));
        default:
            throw new InvalidCommandDukeException();
        }
    }
}

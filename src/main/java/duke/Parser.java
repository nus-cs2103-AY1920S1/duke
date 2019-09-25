package duke;

import java.text.ParseException;

/**
 * Represents a Parser object in the Duke. A <code>Parser</code> object parses
 * string command given by user and creates new Command object.
 */
public class Parser {

    /**
     * Returns a new Command object.
     * If the command is invalid, Duke Exception is thrown.
     *
     * @param fullCommand  String containing command from user input.
     * @return new Command object.
     * @throws DukeException  If command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String firstWord = arr[0];
        switch (firstWord) {
        case "tag":
            return createTagCommand(arr);
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            int indexDelete = Integer.parseInt(arr[1]);
            return new DeleteCommand(indexDelete);
        case "done":
            int indexDone = Integer.parseInt(arr[1]);
            return new DoneCommand(indexDone);
        case "todo":
            return createTodoCommand(arr);
        case "deadline":
            return createDeadlineCommand(arr);
        case "event":
            return createEventCommand(arr);
        case "find":
            return createFindCommand(arr);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static TagCommand createTagCommand(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("OOPS!!! The description of a tag cannot be empty.");
        }
        String tagDescription = arr[1];
        String[] indexTag = tagDescription.split(" ");
        int indexOfTag = Integer.parseInt(indexTag[0]);
        String wordOfTag = indexTag[1];
        if (wordOfTag.isEmpty()) {
            throw new DukeException("OOPS!!! The tag word cannot be empty.");
        }
        return new TagCommand(indexOfTag, wordOfTag);
    }

    private static AddCommand createTodoCommand(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String what = arr[1];
        Todo todo = new Todo(what);
        return new AddCommand(todo);
    }

    private static AddCommand createDeadlineCommand(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String when = arr[1];
        String[] parts = when.split("/by");
        if (parts.length == 1) {
            throw new DukeException("OOPS!!! The time of a deadline cannot be empty.");
        }
        String desc = parts[0];
        String time = parts[1];
        Deadline deadline = new Deadline(desc);
        try {
            deadline.parseTime(time);
        } catch (ParseException ex) {
            throw new DukeException(ex.getMessage());
        }
        return new AddCommand(deadline);
    }

    private static AddCommand createEventCommand(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String where = arr[1];
        String[] partsE = where.split("/at");
        if (partsE.length == 1) {
            throw new DukeException("OOPS!!! The time of an event cannot be empty.");
        }
        String descE = partsE[0];
        String timeE = partsE[1];
        Event event = new Event(descE);
        try {
            event.parseTime(timeE);
        } catch (ParseException ex) {
            throw new DukeException(ex.getMessage());
        }
        return new AddCommand(event);
    }

    private static FindCommand createFindCommand(String[] arr) throws DukeException {
        String word = arr[1];
        if (word.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what to find :-(");
        }
        return new FindCommand(word);
    }
}

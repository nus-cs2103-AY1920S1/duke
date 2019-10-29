package duke.util;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DeadlineCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {

    /**
     * Converts a task from its saved format to a task object.
     * The savedText argument must be in the correct format. An example of a correctly formatted task is
     * "E | 0 | dancing session | 12/1/2010 1212"
     *
     * @param encodedString task in encoded string format
     * @return task object converted from the encoded text
     */
    public static Task convertEncodedStringToTask(String encodedString)
            throws InvalidDateException, InvalidCommandException {

        String[] tokens = encodedString.split("\\s\\|\\s");

        String type = tokens[0];
        boolean isDone = tokens[1].equals("1");
        String description = tokens[2];
        Date date = tokens.length == 4 ? parseDate(tokens[3]) : null;

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "E":
            return new Event(description, date, isDone);
        case "D":
            return new Deadline(description, date, isDone);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Converts list of tasks to saved text format.
     * The returned value can be written into a file.
     *
     * @param tasks list of tasks
     * @return encoded string format of the list of tasks
     */
    public static String convertTasksToEncodedString(ArrayList<Task> tasks) {
        StringBuilder text = new StringBuilder();

        for (Task task : tasks) {
            text.append(task.toEncodedString());
            text.append("\n");
        }

        return text.toString();
    }

    /**
     * Parses a command string.
     * This method always return a {@link Command} object corresponding to the command written by user.
     *
     * @param fullCommand command string from raw user input
     * @return executable {@link Command} object
     */
    public static Command parseCommand(String fullCommand) throws InvalidDateException, InvalidCommandException {
        String commandName = fullCommand.split(" ", 2)[0];

        switch (commandName) {
        case "list":
        case "ls":
            return new ListCommand();
        case "done":
            int doneIndex = Integer.parseInt(fullCommand.split(" ", 2)[1]);
            return new DoneCommand(doneIndex);
        case "delete":
        case "rm":
            int deleteIndex = Integer.parseInt(fullCommand.split(" ", 2)[1]);
            return new DeleteCommand(deleteIndex);
        case "todo":
        case "t":
            String todoDescription = fullCommand.split(" ", 2)[1];
            return new TodoCommand(todoDescription);
        case "event":
        case "e":
            String eventDescription = fullCommand.split(" ", 2)[1];
            Date at = parseDate(eventDescription.split(" /at ", 2)[1]);
            eventDescription = eventDescription.split(" /at ", 2)[0];
            return new EventCommand(eventDescription, at);
        case "deadline":
        case "d":
            String deadlineDescription = fullCommand.split(" ", 2)[1];
            Date by = parseDate(deadlineDescription.split(" /by ", 2)[1]);
            deadlineDescription = deadlineDescription.split(" /by ", 2)[0];
            return new DeadlineCommand(deadlineDescription, by);
        case "find":
            String keyword = fullCommand.split(" ", 2)[1];
            return new FindCommand(keyword);
        default:
            throw new InvalidCommandException();
        }
    }

    private static Date parseDate(String dateString) throws InvalidDateException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }

}

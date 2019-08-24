package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
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
     * Convert a task from its saved format to a task object.
     * The savedText argument must be in the correct format. An
     * example of a correctly formatted task is
     * "E | 0 | dancing session | 12/1/2010 1212"
     *
     * @param savedText the task in saved text format
     * @return the task object converted from the saved text
     */
    public static Task convertSavedTextToTask(String savedText) throws ParseException {
        String[] tokens = savedText.split("\\s\\|\\s");

        String type = tokens[0];
        boolean isDone = tokens[1].equals("1");
        String description = tokens[2];

        Task task;

        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("E")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
            task = new Event(description, dateFormat.parse(tokens[3]));
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
            task = new Deadline(description, dateFormat.parse(tokens[3]));
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Convert list of tasks to saved text format.
     * The returned value can be written into a file.
     *
     * @param tasks the list of tasks
     * @return the saved text format of the list of tasks
     */
    public static String convertTasksToSavedText(ArrayList<Task> tasks) {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toSaveString());
            text.append("\n");
        }
        return text.toString();
    }

    /**
     * Parse a command string.
     * This method always return a {@link Command} object
     * corresponding to the command written by user.
     *
     * @param fullCommand the raw string command
     * @return the executable command object
     */
    public static Command parseCommand(String fullCommand) throws ParseException, InvalidCommandException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        String commandName = fullCommand.split(" ", 2)[0];
        switch (commandName) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            int doneIndex = Integer.parseInt(fullCommand.split(" ", 2)[1]);
            return new DoneCommand(doneIndex);
        case "delete":
            int deleteIndex = Integer.parseInt(fullCommand.split(" ", 2)[1]);
            return new DeleteCommand(deleteIndex);
        case "todo":
            String todoDescription = fullCommand.split(" ", 2)[1];
            return new TodoCommand(todoDescription);
        case "event":
            String eventDescription = fullCommand.split(" ", 2)[1];
            Date at = dateFormat.parse(eventDescription.split(" /at ", 2)[1]);
            eventDescription = eventDescription.split(" /at ", 2)[0];
            return new EventCommand(eventDescription, at);
        case "deadline":
            String deadlineDescription = fullCommand.split(" ", 2)[1];
            Date by = dateFormat.parse(deadlineDescription.split(" /by ", 2)[1]);
            deadlineDescription = deadlineDescription.split(" /by ", 2)[0];
            return new DeadlineCommand(deadlineDescription, by);
        }
        throw new InvalidCommandException();
    }

}

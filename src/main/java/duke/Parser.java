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

    public static String convertTasksToSavedText(ArrayList<Task> tasks) {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toSaveString());
            text.append("\n");
        }
        return text.toString();
    }

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
        case "find":
            String keyword = fullCommand.split(" ", 2)[1];
            return new FindCommand(keyword);
        }
        throw new InvalidCommandException();
    }

}

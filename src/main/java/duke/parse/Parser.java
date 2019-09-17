package duke.parse;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.SearchCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.EmptyToDoDescriptionException;
import duke.exception.UnknownCommandException;
import duke.exception.WrongTaskFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.ParseException;

/**
 * Parser deals with the logic of parsing user inputs.
 */
public class Parser {

    private static Command createToDoTask(String command) throws EmptyToDoDescriptionException {
        Task userTask;
        String task = command.replaceFirst("todo", "");
        if (task.isBlank()) {
            throw new EmptyToDoDescriptionException("The description of a todo cannot be empty. ");
        } else {
            //removes space in the beginning
            task = task.replaceFirst(" ", "");
            userTask = new ToDo(task);
        }
        return new AddCommand(userTask);
    }

    private static Command createSearchCommand(String command) {
        String searchDescription = command.replaceFirst("find ", "");
        return new SearchCommand(searchDescription);
    }

    private static Command createDeleteCommand(String command) {
        int taskNumber = Integer.parseInt(command.substring(7)) - 1;
        return new DeleteCommand(taskNumber);
    }

    private static Command createDoneCommand(String command) {
        int taskNumber = Integer.parseInt(command.substring(5)) - 1;
        return new DoneCommand(taskNumber);
    }

    private static Command createEventTask(String command) throws WrongTaskFormatException {
        try {
            Task userTask;
            String task = command.replaceFirst("event ", "");
            String[] taskInformation = task.split(" /at ");
            userTask = new Event(taskInformation[0], taskInformation[1]);
            return new AddCommand(userTask);
        } catch (IndexOutOfBoundsException ex) {
            throw new WrongTaskFormatException("To create a task, you should follow this format: "
                    + "event <description> /at DD/MM/YYYY HHMM");
        }
    }

    private static Command createDeadlineTask(String command) throws WrongTaskFormatException {
        try {
            Task userTask;
            String task = command.replaceFirst("deadline ", "");
            String[] taskInformation = task.split(" /by ");
            userTask = new Deadline(taskInformation[0], taskInformation[1]);
            return new AddCommand(userTask);
        } catch (IndexOutOfBoundsException ex) {
            throw new WrongTaskFormatException("To create a Deadline, you should follow this format:\n"
                    + "deadline <description> /by DD/MM/YYYY HHMM");
        }

    }

    private static Command createUpdateCommand(String command) throws WrongTaskFormatException {
        String task = command.replaceFirst("update ", "");
        return new UpdateCommand(task);
    }

    /**
     * Parses user input and returns the relevant command to be executed in the main message.
     * @param userInput user command
     * @return Command to be executed in main message
     * @throws DukeException when the user input is not understood
     */
    public static Command parse(String userInput) throws DukeException {
        String[] commandInformation = userInput.split(" ");
        String command = commandInformation[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return createDoneCommand(userInput);
        case "delete":
            return createDeleteCommand(userInput);
        case "find":
            return createSearchCommand(userInput);
        case "todo":
            return createToDoTask(userInput);
        case "deadline":
            return createDeadlineTask(userInput);
        case "event":
            return createEventTask(userInput);
        case "update":
            return createUpdateCommand(userInput);
        default:
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
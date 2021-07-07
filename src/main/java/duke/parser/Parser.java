package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser object.
 */
public class Parser {

    /**
     * Parser for the stored message that takes in a String and returns the parsed task.
     * It parses messages that are stored inside the .txt file by Storage.
     * It will throw a DukeException if the given command is in the wrong format.
     * Only todo, deadline and event tasks can be stored in the data file.
     *
     * @param message The command to be parsed
     * @return The parsed task
     * @throws DukeException Error thrown if the command stored is in the wrong format
     */
    public static Task parseStoredMessage(String message) throws DukeException {
        String[] commands = message.split("\\s{1}\\|\\s{1}");
        Task taskToAdd;
        switch (commands[0]) {
        case "T":
            taskToAdd = new Todo(commands[2], (commands[1].equals("✓")));
            break;
        case "D":
            taskToAdd = new Deadline(commands[2], (commands[1].equals("✓")), commands[3]);
            break;
        case "E":
            taskToAdd = new Event(commands[2], (commands[1].equals("✓")), commands[3]);
            break;
        default:
            assert false : "Task type " + commands[0] + " should not exist";
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return taskToAdd;
    }

    /**
     * Parses a command from the user.
     * It splits the command into its arguments according to the single whitespace delimiter.
     * Allows for done, todo, deadline, event, delete, list, bye and find commands.
     * It will throw a DukeException if the command given is in the wrong format.
     *
     * @param message The message sent by the user
     * @return the parsed command from the user's message
     * @throws DukeException Throws an exception if there is a problem parsing the user's message
     */
    public static Command parseUserMessage(String message) throws DukeException {
        String[] commands = message.split(" ");
        switch (commands[0].toLowerCase()) {
        case "done":
            if (commands.length > 2) {
                throw new DukeException(":( OOPS!!! Your done command is in the wrong format.");
            }
            int completedTaskNum = Integer.parseInt(commands[1]);
            return new DoneCommand(completedTaskNum);
        case "todo":
            Todo newTodo = new Todo(message.substring(4));
            return new AddCommand(newTodo);
        case "deadline":
            Deadline newDeadline = new Deadline(message.substring(8));
            return new AddCommand(newDeadline);
        case "event":
            Event newEvent = new Event(message.substring(5));
            return new AddCommand(newEvent);
        case "delete":
            if (commands.length > 2) {
                throw new DukeException(":( OOPS!!! Your delete command is in the wrong format.");
            }
            int deleteTaskNum = Integer.parseInt(commands[1]);
            return new DeleteCommand(deleteTaskNum);
        case "list":
            if (commands.length > 1) {
                throw new DukeException(":( OOPS!!! Your list command is in the wrong format.");
            }
            return new ListCommand();
        case "bye":
            if (commands.length > 1) {
                throw new DukeException(":( OOPS!!! Your bye command is in the wrong format.");
            }
            return new ExitCommand();
        case "find":
            if (commands.length < 2) {
                throw new DukeException(":( OOPS!!! Your find command is in the wrong format.");
            }
            return new FindCommand(message.substring(4));
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}


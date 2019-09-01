package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {

    public static Task parseStoredMessage(String message) throws DukeException {
        String[] commands = message.split("\\s{1}\\|\\s{1}");
        Task taskToAdd = null;
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
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return taskToAdd;
    }

    public static Command parseUserMessage(String message) throws DukeException {
        String[] commands = message.split(" ");
        switch (commands[0].toLowerCase()) {
            case "done":
                if (commands.length > 2) {
                    throw new DukeException("☹ OOPS!!! Your done command is in the wrong format.");
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
                    throw new DukeException("☹ OOPS!!! Your delete command is in the wrong format.");
                }
                int deleteTaskNum = Integer.parseInt(commands[1]);
                return new DeleteCommand(deleteTaskNum);
            case "list":
                if (commands.length > 1) {
                    throw new DukeException("☹ OOPS!!! Your list command is in the wrong format.");
                }
                return new ListCommand();
            case "bye":
                if (commands.length > 1){
                    throw new DukeException("☹ OOPS!!! Your bye command is in the wrong format.");
                }
                return new ExitCommand();
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}


package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.TodoCommand;

import duke.task.Task;

/**
 * The class that makes sense of user input.
 */
public class Parser {
    protected TaskList taskList;

    /**
     * Public constructor for Parser class.
     *
     * @param taskList TaskList object containing all the methods for creating, getting, and retrieving all the tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user's input.
     *
     * @param input User input to be parsed.
     * @return A command class corresponding to the input.
     * @throws InvalidCommandException If the user input is incomplete (i.e. does not contain task description or date).
     */
    public Command parse(String input) throws InvalidCommandException {
        int ws = input.indexOf(" ");
        String command = input.substring(0, ws == -1 ? input.length() : ws);
        String parameters = input.substring(command.length()).trim();

        switch (command) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(parameters);
        case "todo":
            return new TodoCommand(parameters);
        case "event":
            return new EventCommand(parameters);
        case "deadline":
            return new DeadlineCommand(parameters);
        case "find":
            return new FindCommand(parameters);
        case "delete":
            return new DeleteCommand(parameters);
        case "bye":
            return new ExitCommand();
        default:
            throw new InvalidCommandException(">:( !!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

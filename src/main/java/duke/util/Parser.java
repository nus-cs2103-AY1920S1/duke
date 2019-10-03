package duke.util;

import duke.command.FindCommand;
import duke.command.HelloCommand;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.exception.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Class that encapsulates various parsing methods to parse
 * input Strings and return the appropriate Command Objects.
 * @see duke.command.Command
 * @see DateTime
 */
public class Parser {

    /**
     * Returns a Command object that was parsed from the user input.
     *
     * @param input String containing full user input
     * @return Command objects.
     * @throws DukeException if a generic recoverable runtime error is detected
     * @see Command
     */
    public static Command parseForCommands(String input) throws DukeException {
        String[] parameters = input.split(" ", 2);
        String command = parameters[0].toLowerCase();

        switch (command) {
        case HelloCommand.KEYWORD_ONE:
        case HelloCommand.KEYWORD_TWO:
        case HelloCommand.KEYWORD_THREE:
            return new HelloCommand();
        case ListCommand.KEYWORD:
            return new ListCommand();
        case ByeCommand.KEYWORD:
            return new ByeCommand();
        case DoneCommand.KEYWORD:
            return Parser.parseDoneCommand(parameters);
        case DeleteCommand.KEYWORD:
            return Parser.parseDeleteCommand(parameters);
        case TodoCommand.KEYWORD:
            return Parser.parseTodoCommand(parameters);
        case EventCommand.KEYWORD:
            return Parser.parseEventCommand(parameters);
        case DeadlineCommand.KEYWORD:
            return Parser.parseDeadlineCommand(parameters);
        case FindCommand.KEYWORD:
            return Parser.parseFindCommand(parameters);
        case LoadCommand.KEYWORD:
            return Parser.parseLoadCommand(parameters);
        case SaveCommand.KEYWORD:
            return Parser.parseSaveCommand(parameters);
        default:
            throw new DukeException("At my current stage, I have no idea what that means."
                    + "\nMaybe one day I'll know :(");
        }
    }

    // parses the arguments for remaining parameters to construct a DoneCommand object
    private static DoneCommand parseDoneCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("done"));
        try {
            int taskIndex = Integer.parseInt(parameters[1]);
            return new DoneCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need"
                    + " to tell me the ID of the task\n"
                    + "you're done with!", e);
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide me "
                    + "with a valid task index!\n"
                    + "(That means integer numbers only!)", e);
        }
    }

    // parses the arguments for remaining parameters to construct a DeleteCommand object
    private static DeleteCommand parseDeleteCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("delete"));
        try {
            int taskIndex = Integer.parseInt(parameters[1]);
            return new DeleteCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need"
                    + " to tell me the ID of the task\n"
                    + "you want to delete!", e);
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide me "
                    + "with a valid task index!\n"
                    + "(That means integer numbers only!)", e);
        }
    }

    // parses the arguments for remaining parameters to construct a TodoCommand object
    private static TodoCommand parseTodoCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("todo"));
        try {
            return new TodoCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! You need to give me a description"
                    + " for a todo task!", e);
        }
    }

    // parses the arguments for remaining parameters to construct a EventCommand object
    private static EventCommand parseEventCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("event"));
        try {
            String[] deadlineParams = parameters[1].split(" /at ", 2);
            DateTime at = DateTime.parseString(deadlineParams[1]);
            return new EventCommand(deadlineParams[0], at);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! You need to give me both description\nAND time"
                    + " to create an event task!", e);
        }
    }

    // parses the arguments for remaining parameters to construct a DeadlineCommand object
    private static DeadlineCommand parseDeadlineCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("deadline"));
        try {
            String[] deadlineParams = parameters[1].split(" /by ", 2);
            DateTime by = DateTime.parseString(deadlineParams[1]);
            return new DeadlineCommand(deadlineParams[0], by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! You need to give me both description\nAND time"
                    + " to create a deadline task!", e);
        }
    }

    // parses the arguments for remaining parameters to construct a FindCommand object
    private static FindCommand parseFindCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("find"));
        try {
            return new FindCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need to give me a search word to look for!", e);
        }
    }

    // parses the arguments for remaining parameters to construct a LoadCommand object
    private static LoadCommand parseLoadCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("load"));
        try {
            return new LoadCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! You need to give me a file path to work with!", e);
        }
    }

    // parses the arguments for remaining parameters to construct a SaveCommand object
    private static SaveCommand parseSaveCommand(String[] parameters) throws DukeException {
        assert (parameters[0].equals("save"));
        try {
            return new SaveCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! You need to give me a file path to work with!", e);
        }
    }
}

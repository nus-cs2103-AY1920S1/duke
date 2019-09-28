package duke.parser;

import duke.commands.AddAliasCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteAliasCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListAllAliasesCommand;
import duke.commands.ListCommand;
import duke.commands.ListMatchingAliasesCommand;
import duke.commands.TodoCommand;
import duke.commands.ViewAliasCommand;
import duke.exception.DukeException;
import duke.model.Model;

public class Parser {

    private String[] getCommandTokens(String input) throws DukeException {
        try {
            input = input.trim().toLowerCase();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0].trim();
            if (tokens.length == 1) {
                // If the command is "bye" or "list".
                return new String[]{command, null};
            }

            String arguments = tokens[1].trim();
            if (command.equals("alias")) {
                tokens = input.split(" ", 3);
                command = command + ' ' + tokens[1].trim();
                if (tokens.length == 2) {
                    return new String[]{command, null};
                }
                arguments = tokens[2].trim();
            }
            return new String[]{command, arguments};
        } catch (Exception e) {
            throw new DukeException("The command is invalid!");
        }
    }

    private Command parseDone(String arguments) throws DukeException {
        try {
            return new DoneCommand(Integer.parseInt(arguments));
        } catch (Exception e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private Command parseDelete(String arguments) throws DukeException {
        try {
            return new DeleteCommand(Integer.parseInt(arguments) - 1);
        } catch (Exception e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private Command parseTodo(String arguments) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("The task description cannot be empty.");
        }
        return new TodoCommand(arguments);
    }

    private Command parseDeadline(String arguments) throws DukeException {
        String[] taskDetails = arguments.split("/by");
        try {
            String description = taskDetails[0].trim();
            String by = taskDetails[1].trim();
            if (description.isBlank()) {
                throw new DukeException("The task description cannot be empty.");
            } else if (by.isBlank()) {
                throw new DukeException("The task deadline cannot be empty.");
            }
            return new DeadlineCommand(description, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The task description and deadline cannot be empty.");
        }
    }

    private Command parseEvent(String arguments) throws DukeException {
        String[] taskDetails = arguments.split("/at");
        try {
            String description = taskDetails[0].trim();
            String at = taskDetails[1].trim();
            if (description.isBlank()) {
                throw new DukeException("The task description cannot be empty.");
            } else if (at.isBlank()) {
                throw new DukeException("The task date/time cannot be empty.");
            }
            return new EventCommand(description, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The task description and deadline cannot be empty.");
        }
    }

    private Command parseFind(String arguments) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("The task keyword cannot be empty.");
        }
        return new FindCommand(arguments);
    }

    private Command parseAddAlias(String arguments) throws DukeException {
        String[] tokens = arguments.split(" ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Please supply the keyword mapped to the alias");
        }
        String alias = tokens[0].trim();
        String keyword = tokens[1].trim();
        return new AddAliasCommand(alias, keyword);
    }

    private Command parseDeleteAlias(String arguments) {
        return new DeleteAliasCommand(arguments);
    }

    private Command parseViewAlias(String arguments) {
        return new ViewAliasCommand(arguments);
    }

    private Command parseListMatchingAliases(String arguments) {
        return new ListMatchingAliasesCommand(arguments);
    }

    private Command parseListAllAliases() {
        return new ListAllAliasesCommand();
    }

    /**
     * Translate the input into commands.
     * @param input the text users type in
     * @return the command of the user
     * @throws DukeException if error occurs when parsing input
     */
    public Command parse(Model model, String input) throws DukeException {
        Aliases aliases = model.getAliases();
        String[] commandTokens = this.getCommandTokens(input);
        String keyword = commandTokens[0];
        String arguments = commandTokens[1];
        if (!Aliases.KEYWORDS.contains(keyword) && aliases.containsAlias(keyword)) {
            keyword = aliases.getKeyword(keyword);
        }
        switch (keyword) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return parseDone(arguments);
        case "delete":
            return parseDelete(arguments);
        case "todo":
            return parseTodo(arguments);
        case "deadline":
            return parseDeadline(arguments);
        case "event":
            return parseEvent(arguments);
        case "find":
            return parseFind(arguments);
        case "alias add":
            return parseAddAlias(arguments);
        case "alias delete":
            return parseDeleteAlias(arguments);
        case "alias view":
            return parseViewAlias(arguments);
        case "alias list":
            return parseListMatchingAliases(arguments);
        case "alias all":
            return parseListAllAliases();
        default:
            throw new DukeException("Please enter a valid command!");
        }
    }
}

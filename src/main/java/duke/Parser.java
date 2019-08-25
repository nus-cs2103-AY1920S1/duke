package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FinishCommand;
import duke.command.ListCommand;

public class Parser {

    public static class ParsedCommand {
        public final String input;
        public final String keyword;
        public final String args;
        public final String beforeSlashArgs;
        public final String slashKeyword;
        public final String slashArgs;

        public ParsedCommand(String input) {
            input = input.trim();
            this.input = input;

            this.keyword = input.split("\\s+", 2)[0];
            this.args = input.split("\\s+").length >= 2 ? input.split("\\s+", 2)[1] : null;

            String inputBeforeSlash = input.split("\\s+/", 2)[0];
            String inputAfterSlash = input.split("\\s+/").length >= 2 ? input.split("\\s+/", 2)[1] : null;

            this.beforeSlashArgs = inputBeforeSlash.split("\\s+").length >= 2 ? inputBeforeSlash.split("\\s+", 2)[1] : null;

            if (inputAfterSlash == null) {
                this.slashKeyword = null;
                this.slashArgs = null;
            } else {
                this.slashKeyword = inputAfterSlash.split("\\s+", 2)[0];
                this.slashArgs = inputAfterSlash.split("\\s+").length >= 2 ? inputAfterSlash.split("\\s+", 2)[1] : null;
            }
        }
    }

    private static void validateIsPositiveInteger(String s, String message) throws DukeException {
        try {
            int integer = Integer.parseInt(s);
            if (integer <= 0) {
                throw new DukeException(message);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(message);
        }
    }

    private static void validateNull(String s, String message) throws DukeException {
        if (s != null) {
            throw new DukeException(message);
        }
    }

    private static void validateNotNull(String s, String message) throws DukeException {
        if (s == null) {
            throw new DukeException(message);
        }
    }

    private static void validateString(String s, String target, String message) throws DukeException {
        if (s == null || !s.equals(target)) {
            throw new DukeException(message);
        }
    }

    /**
     * Parses a duke.command into its keyword and its arguments both before and after the slash, given an input string.
     * Does not ensure that the resulting duke.command is legal.
     * @param input duke.command.Command input as a string
     */
    public static Command parse(String input) throws DukeException {
        ParsedCommand parsedCommand = new ParsedCommand(input);
        switch (parsedCommand.keyword) {
        case "bye":
            validateNull(parsedCommand.args, "The bye command should take no arguments.");
            return new ExitCommand();
        case "list":
            validateNull(parsedCommand.args, "The list command should take no arguments.");
            return new ListCommand();
        case "done":
            validateIsPositiveInteger(parsedCommand.args, "The argument for done should be a positive whole number.");
            return new FinishCommand(Integer.parseInt(parsedCommand.args) - 1);
        case "delete":
            validateIsPositiveInteger(parsedCommand.args, "The argument for delete should be a positive whole number.");
            return new DeleteCommand(Integer.parseInt(parsedCommand.args) - 1);
        case "find":
            validateNotNull(parsedCommand.args, "The search keyword should not be empty.");
            return new FindCommand(parsedCommand.args);
        case "todo":
            validateNotNull(parsedCommand.args, "The description of a todo cannot be empty.");
            return new AddTodoCommand(parsedCommand.args);
        case "event":
            validateNotNull(parsedCommand.beforeSlashArgs, "The description of an event cannot be empty.");
            validateString(parsedCommand.slashKeyword, "at", "An event must have an 'at' date or time.");
            validateNotNull(parsedCommand.slashArgs, "The 'at' date or time cannot be empty.");
            return new AddEventCommand(parsedCommand.beforeSlashArgs, parsedCommand.slashArgs);
        case "deadline":
            validateNotNull(parsedCommand.beforeSlashArgs, "The description of a deadline cannot be empty.");
            validateString(parsedCommand.slashKeyword, "by", "A deadline must have a 'by' date or time.");
            validateNotNull(parsedCommand.slashArgs, "The 'by' date or time cannot be empty.");
            return new AddDeadlineCommand(parsedCommand.beforeSlashArgs, parsedCommand.slashArgs);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

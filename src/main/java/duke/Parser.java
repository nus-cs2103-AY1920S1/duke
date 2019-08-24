package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
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

    /**
     * Parses a duke.command into its keyword and its arguments both before and after the slash, given an input string.
     * Does not ensure that the resulting duke.command is legal.
     * @param input duke.command.Command input as a string
     */
    public static Command parse(String input) throws DukeException {
        ParsedCommand parsedCommand = new ParsedCommand(input);
        switch (parsedCommand.keyword) {
        case "bye":
            if (parsedCommand.args != null) {
                throw new DukeException("The bye command should take no arguments.");
            }
            return new ExitCommand();
        case "list":
            if (parsedCommand.args != null) {
                throw new DukeException("The list command should take no arguments.");
            }
            return new ListCommand();
        case "done":
            return new FinishCommand(parsedCommand.args);
        case "delete":
            return new DeleteCommand(parsedCommand.args);
        case "todo":
            if (parsedCommand.args == null) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(parsedCommand.args);
        case "event":
            if (parsedCommand.slashKeyword == null || !parsedCommand.slashKeyword.equals("at")) {
                throw new DukeException("An event must have an 'at' date or time.");
            }
            return new AddEventCommand(parsedCommand.beforeSlashArgs, parsedCommand.slashArgs);
        case "deadline":
            if (parsedCommand.slashKeyword == null || !parsedCommand.slashKeyword.equals("by")) {
                throw new DukeException("A deadline must have a 'by' date or time.");
            }
            return new AddDeadlineCommand(parsedCommand.beforeSlashArgs, parsedCommand.slashArgs);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

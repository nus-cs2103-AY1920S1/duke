package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;


/**
 * Parse the user input to valid command.
 *
 */
public class Parser {
    /**
     * parse the user input to valid command.
     * @param input user input
     * @return Command
     */
    public static Command parse(String input) {
        assert input.trim().length() > 0 : input;
        String[] msg = input.trim().split(" ", 2);
        if (msg[0].equals("bye")) {
            return new ExitCommand(msg);
        } else if (msg[0].equals("list")) {
            return new ListCommand(msg);
        } else if (msg[0].equals("done")) {
            return new DoneCommand(msg);
        } else if (msg[0].equals("todo")) {
            return new AddTodoCommand(msg);
        } else if (msg[0].equals("deadline")) {
            return new AddDeadlineCommand(msg);
        } else if (msg[0].equals("event")) {
            return new AddEventCommand(msg);
        } else if (msg[0].equals("delete")) {
            return new DeleteCommand(msg);
        } else if (msg[0].equals("find")) {
            return new FindCommand(msg);
        } else if (msg[0].equals("help")) {
            return new HelpCommand(msg);
        } else {
            return new InvalidCommand();
        }
    }
}

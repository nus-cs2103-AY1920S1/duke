package duke.util;


import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.InvalidCommand;

public class Parser {

    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.contains("done")) {
            return new DoneCommand();
        } else if (fullCommand.contains("delete")) {
            return new DeleteCommand();
        } else if (fullCommand.contains("todo") || fullCommand.contains("deadline") || fullCommand.contains("event")) {
            return new AddCommand();
        } else if (fullCommand.contains("find")) {
            return new FindCommand();
        }
        return new InvalidCommand();
    }

}

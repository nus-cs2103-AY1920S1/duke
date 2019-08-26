package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.exception.DukeException;

public class Parser {

    public static Command parse(String command) throws DukeException {
        if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.length() >= 4 && command.substring(0, 4).equalsIgnoreCase("done")) {
            return new DoneCommand(command);
        } else if (command.length() >= 6 && command.substring(0, 6).equalsIgnoreCase("delete")) {
            return new DeleteCommand(command);
        } else {  // add task or wrong command
            String[] temp = command.split(" ");
            if (temp[0].equalsIgnoreCase("deadline")
                    || temp[0].equalsIgnoreCase("event")
                    || temp[0].equalsIgnoreCase("todo")) {
                return new AddCommand(command);
            } else {
                throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

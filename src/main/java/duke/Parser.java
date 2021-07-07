package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.LendCommand;
import duke.command.ListCommand;
import duke.command.OweCommand;
import duke.command.PayCommand;
import duke.command.ReceiveCommand;
import duke.command.SummaryCommand;

import java.util.Arrays;

/**
 * Represents a reader. A <code>Parser</code> parses and understands a given
 * string and tells the Ui which command to be run next.
 */
public class Parser {
    /**
     * Reads the given string and processes it accordingly to return
     * an appropriate Command.
     * @param command given string.
     * @return matching Command object as implied from the given string.
     * @throws DukeException if the format of the given string is invalid.
     */
    public static Command parse(String command) throws DukeException {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "summary":
            return new SummaryCommand();
        default:
            // check if the first word of the line is 'done'
            String[] commandList = command.split(" ");
            String firstWord = commandList[0];
            switch (firstWord) {
            case "done": case "delete":
                if (commandList.length == 1) {
                    throw new DukeException("The task index cannot be empty!");
                }
                switch (firstWord) {
                case "done":
                    return new DoneCommand(Integer.parseInt(commandList[1]));
                case "delete":
                    return new DeleteCommand(Integer.parseInt(commandList[1]));
                default:
                    break;
                }
                break;
            case "find":
                String keyword = String.join(" ", Arrays.copyOfRange(commandList, 1, commandList.length));
                return new FindCommand(keyword);
            case "owe": case "lend": case "pay": case "receive":
                String name = commandList[1];
                double amount = Double.parseDouble(commandList[2]);
                switch (firstWord) {
                case "owe":
                    return new OweCommand(name, amount);
                case "lend":
                    return new LendCommand(name, amount);
                case "pay":
                    return new PayCommand(name, amount);
                case "receive":
                    return new ReceiveCommand(name, amount);
                default:
                    break;
                }
                break;
            default:
                return new AddCommand(command);
            }
        }
        return null;
    }
}

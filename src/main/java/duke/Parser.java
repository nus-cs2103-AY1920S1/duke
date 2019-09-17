package duke;

import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.DeadlineCommand;
import duke.command.DefineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Class to handle user input.
 */
public class Parser {
    public static String[] shortcuts = {"b", "l", "d", "dead", "e", "t", "del", "f", "def"};

    static Command parse(String input) throws DukeException {
        CommandEnum command;

        String[] strArr = input.split(" ");
        String first = strArr[0];
        String next = String.join(" ", strArr).replace(first, "");

        try {
            command = CommandEnum.valueOf(first.toUpperCase());
        } catch (IllegalArgumentException e) {
            if (first.equals(shortcuts[0])) {
                command = CommandEnum.BYE;
            } else if (first.equals(shortcuts[1])) {
                command = CommandEnum.LIST;
            } else if (first.equals(shortcuts[2])) {
                command = CommandEnum.DONE;
            } else if (first.equals(shortcuts[3])) {
                command = CommandEnum.DEADLINE;
            } else if (first.equals(shortcuts[4])) {
                command = CommandEnum.EVENT;
            } else if (first.equals(shortcuts[5])) {
                command = CommandEnum.TODO;
            } else if (first.equals(shortcuts[6])) {
                command = CommandEnum.DELETE;
            } else if (first.equals(shortcuts[7])) {
                command = CommandEnum.FIND;
            } else if (first.equals(shortcuts[8])) {
                command = CommandEnum.DEFINE;
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        switch (command) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case DONE:
                return new DoneCommand(next);
            case DEADLINE:
                return new DeadlineCommand(next);
            case EVENT:
                return new EventCommand(next);
            case TODO:
                return new TodoCommand(next);
            case DELETE:
                return new DeleteCommand(next);
            case FIND:
                return new FindCommand(next);
            case DEFINE:
                return new DefineCommand(next);
            default:
                throw new DukeException("How is it even possible to reach this line of code?");
        }
    }
}

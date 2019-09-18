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

import java.util.HashMap;

/**
 * Class to handle user input.
 */
public class Parser {
    private static HashMap<String, CommandEnum> shortcuts = new HashMap<>();

    static {
        shortcuts.put("b", CommandEnum.BYE);
        shortcuts.put("bye", CommandEnum.BYE);
        shortcuts.put("dead", CommandEnum.DEADLINE);
        shortcuts.put("deadline", CommandEnum.DEADLINE);
        shortcuts.put("def", CommandEnum.DEFINE);
        shortcuts.put("define", CommandEnum.DEFINE);
        shortcuts.put("del", CommandEnum.DELETE);
        shortcuts.put("delete", CommandEnum.DELETE);
        shortcuts.put("d", CommandEnum.DONE);
        shortcuts.put("done", CommandEnum.DONE);
        shortcuts.put("e", CommandEnum.EVENT);
        shortcuts.put("event", CommandEnum.EVENT);
        shortcuts.put("f", CommandEnum.FIND);
        shortcuts.put("find", CommandEnum.FIND);
        shortcuts.put("l", CommandEnum.LIST);
        shortcuts.put("list", CommandEnum.LIST);
        shortcuts.put("t", CommandEnum.TODO);
        shortcuts.put("todo", CommandEnum.TODO);
    }

    static Command parse(String input) throws DukeException {
        CommandEnum command;

        String[] strArr = input.split(" ");
        String first = strArr[0];
        String next = String.join(" ", strArr).replace(first, "");

        if (!shortcuts.containsKey(first)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        command = shortcuts.get(first);

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

    public static void replaceShortcut(String toReplace, String newKeyword) throws DukeException {
        if (toReplace.equals(newKeyword)) {
            throw new DukeException("Why are u replacing the shortcut with the exact same thing?");
        }
        if (!shortcuts.containsKey(toReplace)) {
            throw new DukeException("☹ OOPS!!! Please enter a valid shortcut to be replaced.");
        }
        if (shortcuts.containsKey(newKeyword)) {
            throw new DukeException("☹ OOPS!!! Please enter a new keyword that is not in use.");
        }

        CommandEnum temp = shortcuts.get(toReplace);
        shortcuts.remove(toReplace);
        shortcuts.put(newKeyword, temp);
    }
}

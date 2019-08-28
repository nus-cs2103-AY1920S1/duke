package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] strArr = input.split(" ");
        String command = strArr[0];
        String next = String.join(" ", strArr).replace(command, "");
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(next);
        case "deadline":
            return new DeadlineCommand(next);
        case "event":
            return new EventCommand(next);
        case "todo":
            return new TodoCommand(next);
        case "delete":
            return new DeleteCommand(next);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

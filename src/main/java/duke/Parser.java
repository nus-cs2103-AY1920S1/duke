package duke;

import duke.exception.DukeException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeUnknownInputException;

import java.util.Arrays;

import static duke.task.TaskType.*;

/**
 * Deals with making sense of commands.
 */
public class Parser {
    public static Command parse(String command) throws DukeException {
        // remove trailing/leading whitespace and split by whitespace(s)
        command = command.strip();
        String[] commands = command.split("[ ]+");
        String[] args = Arrays.copyOfRange(commands, 1, commands.length);

        switch (commands[0]) {
        case "todo":
            if (args.length == 0) {
                throw new DukeMissingDescriptionException(
                        "Parser: :'( OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(TODO, args, false);
        case "event":
            return new AddCommand(EVENT, args, false);
        case "deadline":
            String[] dArgs = String.join(" ", args).split(" /by ");
            String deadline = dArgs[1];
            // enforce example format 2/12/2019 1800
            if (deadline.split(" ").length != 2
                    || deadline.split(" ")[0].split("/").length != 3
                    || Integer.valueOf(deadline.split(" ")[1]) < 0
                    || Integer.valueOf(deadline.split(" ")[1]) > 2400) {
                throw new DukeUnknownInputException("Parser: Unknown deadline String format passed :(");
            }
            return new AddCommand(DEADLINE, args, false);
        case "done":
            int doneIdx = Integer.valueOf(args[0]);
            return new DoneCommand(doneIdx, false);
        case "delete":
            int deleteIdx = Integer.valueOf(args[0]);
            return new DeleteCommand(deleteIdx, false);
        case "list":
            return new ListCommand(false);
        case "bye":
            return new ExitCommand(true);
        default:
            throw new DukeMissingDescriptionException(
                    "Parser: :'( OOPS!!! I'm sorry, but I don't know what that means...");
        }
    }
}

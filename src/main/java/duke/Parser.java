package duke;

import duke.command.AddCommand;
import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser class deals with making sense of the user command.
 *
 * @author scwaterbear
 */
class Parser {

    /**
     * Categorizes a broad range of user input to a narrower set of commands.
     *
     * @param fullCommand user input.
     * @return Command user command.
     * @throws DukeException If IndexOutOfBoundException has occurred or user input is invalid or unknown.
     */
    static Command parse(String fullCommand) throws DukeException {

        if (fullCommand.equals("list")) {
            return new ListCommand();

        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();

        } else if (fullCommand.startsWith("todo")) {
            try {
                fullCommand = fullCommand.substring(5);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! Todos must have a description");
            }
            return new AddCommand(new Todo(fullCommand));

        } else if (fullCommand.startsWith("deadline")) {
            try {
                fullCommand = fullCommand.substring(9);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! Deadlines must have a description");
            }

            String[] arguments = fullCommand.split("/by");
            if (arguments.length != 2) {
                throw new DukeException(" ☹ OOPS!!! Deadlines must have exactly one deadline");
            }
            return new AddCommand(
                    new Deadline(
                            arguments[0].trim(),
                            DateTimeHelper.convertDateToString(arguments[1].trim())
                    )
            );

        } else if (fullCommand.startsWith("event")) {
            try {
                fullCommand = fullCommand.substring(6);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! Events must have a description");
            }

            String[] arguments = fullCommand.split("/at");
            if (arguments.length != 2) {
                throw new DukeException(" ☹ OOPS!!! Events must have exactly one time");
            }
            return new AddCommand(
                    new Event(
                            arguments[0].trim(),
                            DateTimeHelper.convertDateToString(arguments[1].trim())
                    )
            );

        } else if (fullCommand.matches("delete ([1-9]|[1-9][0-9]|100)")) {
            int deleteIndex = Integer.parseInt(fullCommand.substring(7));
            return new DeleteCommand(deleteIndex - 1);

        } else if (fullCommand.matches("done ([1-9]|[1-9][0-9]|100)")) {
            int displayNumber = Integer.parseInt(fullCommand.substring(5));
            return new DoneCommand(displayNumber - 1);

        } else if (fullCommand.startsWith("find")) {
            try {
                fullCommand = fullCommand.substring(5);
                return new FindCommand(fullCommand);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! You must input a search term");
            }
        } else if (fullCommand.startsWith("archive")) {
            return new ArchiveCommand();

        } else {
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

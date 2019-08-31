package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

class Parser {

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
            return new AddCommand(new Deadline(arguments[0].trim(), DateTimeHelper.convertDateToString(arguments[1].trim())));

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
            return new AddCommand(new Event(arguments[0].trim(), DateTimeHelper.convertDateToString(arguments[1].trim())));

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
            }catch (IndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! You must input a search term");
            }

        } else {
            return new UnknownCommand();
        }
    }
}

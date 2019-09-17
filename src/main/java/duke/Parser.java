package duke;

import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;

import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.DukeException;
import duke.exception.EmptyDateTimeDukeException;
import duke.exception.EmptyTaskNumberException;
import duke.exception.UnknownInputDukeException;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command parse(String input) throws DukeException {
        assert input != null;

        String[] inputArr = input.split(" ", 2);
        String cmd = inputArr[0];

        List<String> list = Arrays.asList(inputArr);

        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            int taskNum = Integer.valueOf(inputArr[1]);
            return new DoneCommand(taskNum);
        case "todo":
            String desc = inputArr[1];
            return new AddCommand(new Todo(desc));
        case "deadline":
            try {
                String taskAndDate = inputArr[1];
                String[] deadline = taskAndDate.split(" /by ", 2);
                return new AddCommand(new Deadline(deadline[0], deadline[1]));
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new EmptyDateTimeDukeException();
            }
        case "event":
            try {
                String descAndDate = inputArr[1];
                String[] event = descAndDate.split(" /at", 2);
                return new AddCommand(new Event(event[0], event[1]));
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new EmptyDateTimeDukeException();
            }
        case "delete":
            try {
                int deleteNum = Integer.valueOf(inputArr[1]);
                return new DeleteCommand(deleteNum);
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new EmptyTaskNumberException();
            }
        case "find":
            return new FindCommand(inputArr[1]);
        default:
            throw new UnknownInputDukeException();
        }

    }
}

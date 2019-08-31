package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new exitCommand();
        } else if (fullCommand.equals("list")) {
            return new listCommand();
        } else if (fullCommand.startsWith("done")) {
            char num = fullCommand.charAt(fullCommand.length() - 1);
            int index = Character.getNumericValue(num);
            return  new doneCommand(index);
        } else if (fullCommand.startsWith("delete")) {
            char num = fullCommand.charAt(fullCommand.length() - 1);
            int index = Character.getNumericValue(num);
            return new deleteCommand(index);
        } else if (fullCommand.startsWith("todo")) {
            if (fullCommand.trim().length() == 4) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String descrip = fullCommand.substring(5);
            Task task = new Todo(descrip);
            return new addCommand(task);
        } else if (fullCommand.startsWith("event")) {
            if (fullCommand.trim().length() == 5) {
                throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
            }
            String[] event = fullCommand.substring(6).split(" /at ");
            if (event.length != 2) {
                throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
            }
            Task task = new Event(event[0], event[1]);
            return new addCommand(task);
        } else if (fullCommand.startsWith("deadline")) {
            if (fullCommand.trim().length() == 8) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] event = fullCommand.substring(9).split(" /by ");
            if (event.length != 2) {
                throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
            }
            Task task = new Deadline(event[0], event[1]);
            return new addCommand(task);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

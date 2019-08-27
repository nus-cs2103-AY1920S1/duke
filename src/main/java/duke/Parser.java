package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    public Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            int doneIndex = Integer.parseInt(input.split(" ")[1]) - 1; // possible error here
            return new DoneCommand(doneIndex);
        } else if (input.startsWith("delete")) {
            int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1; // possible error here
            return new DeleteCommand(deleteIndex);
        } else { // add task
            Task task;
            if (input.startsWith("todo")) {
                input = input.replaceFirst("^todo", "");
                if (input.substring(input.indexOf(" ") + 1).isEmpty()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
                } else {
                    task = new ToDo(input.substring(input.indexOf(" ") + 1));
                }
            } else if (input.startsWith("deadline")) {
                task = new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        DateTime.parse(input.substring(input.indexOf("/") + 4)));
            } else if (input.startsWith("event")) {
                task = new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        DateTime.parse(input.substring(input.indexOf("/") + 4)));
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
            return new AddCommand(task);
        }
    }
}
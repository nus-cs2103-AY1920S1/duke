package duke.handler;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parser is the logic handler of Duke. It breaks down each command entered
 * by the user and returning a Command object of its corresponding type.
 */
public class Parser {

    public Parser() {}

    public static Command parse(String fullCommand) throws DukeException {
        String line = fullCommand.toLowerCase();
        if (line.length() >= 3 && line.substring(0, 3).equals("bye")) {
            ExitCommand exitCommand = new ExitCommand(line);
            return exitCommand;
        } else if (line.length() >= 4 && line.substring(0, 4).equals("list")) {
            ListCommand listCommand = new ListCommand(line);
            return listCommand;
        } else if (line.length() >= 6 && line.substring(0, 6).equals("delete")) {
            DeleteCommand deleteCommand = new DeleteCommand(line);
            return deleteCommand;
        } else if (line.length() >= 4 && line.substring(0, 4).equals("done")) {
            DoneCommand doneCommand = new DoneCommand(line);
            return doneCommand;
        } else {
            Task newTask = null;
            if (line.length() >= 4 && line.substring(0, 4).equals("todo")) {
                String newLine = line.substring(5);
                if (newLine.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    newTask = new ToDo(newLine);
                    AddCommand addCommand = new AddCommand(line, newTask);
                    return addCommand;
                }
            } else if (line.length() >= 8 && line.substring(0, 8).equals("deadline")) {
                int index = line.indexOf("/by ");
                if (index == -1 || line.substring(9, index).length() == 0 || line.substring(index + 3).length() == 0 ) {
                    throw new DukeException("☹ OOPS!!! Please specify a [description of deadline] /by [date of deadline].");
                } else {
                    String description = line.substring(9, index - 1);
                    String by = line.substring(index + 4, line.length());
                    newTask = new Deadline(description, by);
                    AddCommand addCommand = new AddCommand(line, newTask);
                    return addCommand;
                }
            } else if (line.length() >= 5 && line.substring(0, 5).equals("event")) {
                int index = line.indexOf("/at ");
                if (index == -1 || line.substring(6, index).length() == 0 || line.substring(index + 3).length() == 0) {
                    throw new DukeException("☹ OOPS!!! Please specify a [description of event] /at [date of event]");
                } else {
                    String description = line.substring(6, index - 1);
                    String at = line.substring(index + 4, line.length());
                    newTask = new Event(description, at);
                    AddCommand addCommand = new AddCommand(line, newTask);
                    return addCommand;
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

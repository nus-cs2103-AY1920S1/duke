package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The parser of the user command.
 */
public class Parser {

    /**
     * Parses the input command.
     *
     * @param fullCommand The full string of user's command.
     * @return The Command object to react.
     * @throws DukeException When input is in wrong format.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("done")) {
            //char num = fullCommand.charAt(fullCommand.length() - 1);
            String[] command = fullCommand.split(" ");
            String num = command[1];
            int index = Integer.valueOf(num);
            return  new DoneCommand(index);
        } else if (fullCommand.startsWith("delete")) {
            //char num = fullCommand.charAt(fullCommand.length() - 1);
            String[] command = fullCommand.split(" ");
            String num = command[1];
            int index = Integer.valueOf(num);
            return new DeleteCommand(index);
        } else if (fullCommand.startsWith("todo")) {
            Task task = createTask(fullCommand);
            return new AddCommand(task);
        } else if (fullCommand.startsWith("event")) {
            Task task = createTask(fullCommand);
            return new AddCommand(task);
        } else if (fullCommand.startsWith("deadline")) {
            Task task = createTask(fullCommand);
            return new AddCommand(task);
        } else if (fullCommand.startsWith("find")) {
            String keyword = fullCommand.substring(5);
            return new FindCommand(keyword);
        } else if (fullCommand.startsWith("edit")) {
            if (fullCommand.length() < 8) {
                throw new DukeException("Wrong edit command.");
            }
            String[] command = fullCommand.split(" ");
            String num = command[1];
            int index = Integer.valueOf(num);
            String newTimeString = command[2];
            if (command.length < 4) {
                throw new DukeException("Wrong edit command.");
            }
            for (int i = 3; i < command.length; i++) {
                newTimeString += (" " + command[i]);
            }
            Date newTime = stringToDate(newTimeString);
            return new EditCommand(index, newTime);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Translate string to date.
     *
     * @param dateInString The string that will be translate.
     * @return The date translated from the string input.
     * @throws DukeException When the string is in wrong format.
     */
    public static Date stringToDate(String dateInString) throws DukeException {
        Date actualTime;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            actualTime = formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new DukeException("Time format wrong");
        }

        return actualTime;
    }

    /**
     * Creates new task.
     *
     * @param content The content of the task.
     * @return Task created from the content.
     * @throws DukeException When content is in wrong format.
     */
    private static Task createTask(String content) throws DukeException {
        Task task;

        if (content.startsWith("todo")) {
            if (content.trim().length() == 4) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String descrip = content.substring(5);
            task = new Todo(descrip);
        } else if (content.startsWith("event")) {
            if (content.trim().length() == 5) {
                throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
            }
            String[] event = content.substring(6).split(" /at ");
            if (event.length != 2) {
                throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
            }
            task = new Event(event[0], event[1]);
        } else if (content.startsWith("deadline")) {
            if (content.trim().length() == 8) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] event = content.substring(9).split(" /by ");
            if (event.length != 2) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline is wrong.");
            }
            Date actualTime = stringToDate(event[1]);
            task = new Deadline(event[0], actualTime);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return task;
    }

}

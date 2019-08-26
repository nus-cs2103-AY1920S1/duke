package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Create a Parser file to parse user input into Commands understood by the programme.
 */
public class Parser {
    /**
     * Parse user input into Commands understood by the programme.
     *
     * @param s Input string.
     * @return A specific Command which will be executed.
     * @throws DukeException If an input is not formatted properly.
     */
    public static Command parse(String s) throws DukeException {
        String[] strArr = s.split(" ");
        String[] temp;
        String action = strArr[0].toLowerCase();
        Command c;
        String description;
        String keyword;
        Task task;
        int n;

        switch (action) {
        case "list":
            c = new ListCommand();
            break;

        case "done":
            if (s.length() < 6) {
                throw new DukeException("Please write in this format: done X\nWhere X is a number in the list");
            }
            n = Integer.parseInt(strArr[1]) - 1;
            c = new DoneCommand(n);
            break;

        case "delete":
            if (s.length() < 7) {
                throw new DukeException("Please write in this format: delete X\nWhere X is a number in the list");
            }
            n = Integer.parseInt(strArr[1]) - 1;
            c = new DeleteCommand(n);
            break;

        case "find":
            if (s.length() < 6) {
                throw new DukeException("Please write in this format: find X\nWhere X is the string to find");
            }
            keyword = s.substring(5).toLowerCase();
            c = new FindCommand(keyword);
            break;

        case "todo":
            if (s.length() < 6) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            description = s.substring(5);
            task = new ToDo(description);
            c = new AddCommand(task);
            break;

        case "deadline":
            if (s.length() < 10) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            temp = s.split("/by");
            if (temp.length < 2) {
                throw new DukeException("Please specify the deadline time using /by.");
            }
            description = temp[0].substring(9).trim();
            String by = temp[1].trim();
            try {
                task = new Deadline(description, by);
            } catch (Exception e) {
                throw new DukeException("Please input a date in this format : dd/MM/yy HH:mm");
            }
            c = new AddCommand(task);
            break;

        case "event":
            if (s.length() < 7) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            temp = s.split("/at");
            if (temp.length < 2) {
                throw new DukeException("Please specify the event time using /at.");
            }
            description = temp[0].substring(6).trim();
            String at = temp[1].trim();
            try {
                task = new Event(description, at);
            } catch (Exception e) {
                throw new DukeException("Please input a date in this format : dd/MM/yy HH:mm");
            }
            c = new AddCommand(task);
            break;

        case "bye":
            c = new ExitCommand();
            break;

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }
}
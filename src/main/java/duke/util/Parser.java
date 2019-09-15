package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Creates a Parser file to parse user input into Commands understood by the programme.
 */
public class Parser {
    /**
     * Parses user input into Commands understood by the programme.
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
        case "meow":
            c = new HelpCommand();
            break;

        case "list":
            if (strArr.length == 1) {
                c = new ListCommand();
                break;
            } else {
                throw new DukeException("Wof?");
            }

        case "done":
            if (s.length() < 6) {
                throw new DukeException("Please write in this format: done X\nWhere X is a number in the list");
            }
            assert (s.length() >= 6);
            try {
                n = Integer.parseInt(strArr[1]) - 1;
                c = new DoneCommand(n);
                break;
            } catch (NumberFormatException e) {
                throw new DukeException("X should be a number in the list");
            }

        case "delete":
            if (s.length() < 7) {
                throw new DukeException("Please write in this format: delete X\nWhere X is a number in the list");
            }
            assert (s.length() >= 7);
            try {
                n = Integer.parseInt(strArr[1]) - 1;
                c = new DeleteCommand(n);
                break;
            } catch (NumberFormatException e) {
                throw new DukeException("X should be a number in the list");
            }

        case "find":
            if (s.length() < 6) {
                throw new DukeException("Please write in this format: find X\nWhere X is the string to find");
            }
            assert (s.length() >= 6);
            try {
                keyword = s.substring(5).toLowerCase();
                c = new FindCommand(keyword);
                break;
            } catch (NumberFormatException e) {
                throw new DukeException("X should be a number in the list");
            }

        case "todo":
            if (s.length() < 6) {
                throw new DukeException("Wof! The description of a todo cannot be empty.");
            }
            assert (s.length() >= 6);
            description = s.substring(5);
            task = new ToDo(description);
            c = new AddCommand(task);
            break;

        case "deadline":
            if (s.length() < 10) {
                throw new DukeException("Wof! The description of a deadline cannot be empty.");
            }
            assert (s.length() >= 10);
            temp = s.split("/by");
            if (temp.length < 2) {
                throw new DukeException("Please specify the deadline time using /by.");
            }
            description = temp[0].substring(9).trim();
            String by = temp[1].trim();
            try {
                task = new Deadline(description, by);
                c = new AddCommand(task);
                break;
            } catch (Exception e) {
                throw new DukeException("Please input a date in this format : MM/dd/yy HH:mm");
            }

        case "event":
            if (s.length() < 7) {
                throw new DukeException("Wof! The description of a event cannot be empty.");
            }
            assert (s.length() >= 7);
            temp = s.split("/at");
            if (temp.length < 2) {
                throw new DukeException("Please specify the event time using /at.");
            }
            description = temp[0].substring(6).trim();
            String at = temp[1].trim();
            try {
                task = new Event(description, at);
                c = new AddCommand(task);
                break;
            } catch (Exception e) {
                throw new DukeException("Please input a date in this format : MM/dd/yy HH:mm");
            }

        case "bye":
            c = new ExitCommand();
            break;

        default:
            throw new DukeException("Wof? Me no understand");
        }
        return c;
    }
}
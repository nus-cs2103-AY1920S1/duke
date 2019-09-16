package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidTodoDescription;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import java.util.Date;
import java.util.Locale;

/**
 * This class allows the <code>Duke</code> application to understand the user's input
 * and executes the necessary checks to ensure that tasks are added neatly in
 * the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class Parser {

    /**
     * This method checks the to-do
     * tasks to ensure that the user input
     * includes the task to be added into the task list.
     *
     * @param tasks The user's input string in separated into an array.
     * @throws InvalidTodoDescription If there is no description of the tasks.
     */
    private static void todoCheck(String[] tasks) throws InvalidTodoDescription {
        if (tasks.length <= 1) {
            throw new InvalidTodoDescription();
        }
    }

    /**
     * This method checks the <code>deadline</code>
     * tasks to ensure that the user input includes the task
     * to be added into the task list and also the date and time
     * of the task.
     *
     * @param tasks The user's input string in separated into an array.
     * @param userInput The user's input string.
     * @throws InvalidDeadlineException If there is no specified date or task.
     */
    private static void deadlineCheck(String[] tasks, String userInput) throws InvalidDeadlineException {
        if (tasks.length <= 1) {
            throw new InvalidDeadlineException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!userInput.contains("/by")) {
            throw new InvalidDeadlineException("OOPS!!! Deadline must include /by (date to complete task).");
        }
        /*
        If contains date and time, userInput should split to more than one part
        when splitting the userInput at "/by".
         */
        String[] data = userInput.split(" /by ");
        if (data.length == 1) {
            throw new InvalidDeadlineException(
                    "OOPS!!! You must include the date and the time of the event after '/by'!");
        }
    }

    /**
     * This method checks the <code>event</code>
     * tasks to ensure that the user input includes the task
     * to be added into the task list and the date and time
     * of the event.
     *
     * @param tasks The user's input string in separated into an array.
     * @param userInput The user's input string.
     * @throws DukeException If there is no specified date or task.
     */
    private static void eventCheck(String[] tasks, String userInput) throws InvalidEventException {
        if (tasks.length <= 1) {
            throw new InvalidEventException("OOPS!!! The description of a event cannot be empty.");
        } else if (!userInput.contains("/at")) {
            throw new InvalidEventException(
                    "OOPS!!! Event must include /at (time of event).");
        }
        /*
        If contains date and time, userInput should split to more than one part
        when splitting the userInput at "/at".
         */
        String[] data = userInput.split(" /at ");
        if (data.length == 1) {
            throw new InvalidEventException(
                    "OOPS!!! You must include the date and the time of the event after '/at'!");
        }
    }

    /**
     * This method checks to ensure that the user provides
     * a keyword to search for from the task list.
     *
     * @param tasks The user's input split into an array of strings.
     * @throws DukeException If there is no keyword to search for.
     */
    private static void findCheck(String[] tasks) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! Please tell me what you want to find!!");
        }
    }

    /**
     * This method checks to ensure that the user provides
     * a keyword to search for from the task list.
     *
     * @param tasks The user's input split into an array of strings.
     * @throws DukeException If there is no keyword to search for.
     */
    private static void sortCheck(String[] tasks) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException(
                    "OOPS!!! Please tell me what if you want to sort deadlines or events!");
        } else if (tasks.length > 2) {
            throw new DukeException(
                    "OOPS!!! Please choose to 'sort deadline' or 'sort event");
        }
    }

    /**
     * This method when called reformats the date input by the user to a Date object.
     *
     * @param date The date string to be reformatted.
     * @return The <code>Date</code> in a formatted manner.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public static Date dateFormatter(String date) throws DukeException {
        String dateFormat = "dd/MM/uuuu HHmm";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat, Locale.US)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            throw new DukeException(ex.getMessage());
        }
        try {
            Date parseDate = formatter.parse(date);
            return parseDate;
        } catch (ParseException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * This method when called will result in the returning of a <code>Command</code>
     * which can be executed to result in either a deletion of the task, an addition of
     * a task or to mark a task as done from the task list.
     *
     * @param command The user's input string.
     * @return A class of command depending on the type of user input.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else {
            String[] task = command.split(" ");
            String instruction = task[0];

            if (instruction.equals("done")) {
                int taskNumber = Integer.parseInt(task[1]);
                return new DoneCommand(taskNumber - 1);
            } else if (instruction.equals("delete")) {
                try {
                    int taskNumber = Integer.parseInt(task[1]);
                    return new DeleteCommand(taskNumber - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Provide a task number to delete!");
                }
            } else {
                switch (instruction) {
                case "todo": {
                    try {
                        todoCheck(task);
                        Task todo = new Todo(command.substring(5));
                        return new AddCommand(todo);
                    } catch (DukeException e) {
                        throw new DukeException(e.getMessage());
                    }
                }
                case "deadline": {
                    try {
                        deadlineCheck(task, command);
                        Task deadline = new Deadline(command.substring(9, command.indexOf("/by") - 1),
                                dateFormatter(command.substring(command.indexOf("/by") + 4)));
                        return new AddCommand(deadline);
                    } catch (DukeException e) {
                        throw new DukeException(e.getMessage());
                    }
                }
                case "event": {
                    try {
                        eventCheck(task, command);
                        Task event = new Event(command.substring(6, command.indexOf("/at") - 1),
                                dateFormatter(command.substring(command.indexOf("/at") + 4)));
                        return new AddCommand(event);
                    } catch (DukeException e) {
                        throw new DukeException(e.getMessage());
                    }
                }
                case "find": {
                    try {
                        findCheck(task);
                        return new FindTaskCommand(command.substring(5).trim().toLowerCase());
                    } catch (DukeException e) {
                        throw new DukeException(e.getMessage());
                    }
                }
                case "sort": {
                    try {
                        sortCheck(task);
                        return new SortCommand(task[1]);
                    } catch (DukeException e) {
                        throw new DukeException(e.getMessage());
                    }
                }
                default: {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                }
            }
        }
    }

}
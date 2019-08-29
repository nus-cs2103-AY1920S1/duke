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
     * @param tasks The user's input string in separated into an array.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    private static void todoCheck(String[] tasks) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
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
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    private static void deadlineCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!userInput.contains("/by")) {
            throw new DukeException("OOPS!!! Deadline must include /by (date to complete task).");
        } else if (userInput.substring(userInput.indexOf("/by") + 3).equals("")
                || userInput.substring(userInput.indexOf("/by") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the date to complete task after /by command.");
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
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    private static void eventCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else if (!userInput.contains("/at")) {
            throw new DukeException("OOPS!!! Event must include /at (time of event).");
        } else if (userInput.substring(userInput.indexOf("/at") + 3).equals("")
                || userInput.substring(userInput.indexOf("/at") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the time of event after /at.");
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
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
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
                        System.err.println("Something went wrong: " + e.getMessage());
                        break;
                    }
                }
                case "deadline": {
                    try {
                        deadlineCheck(task, command);
                        Task deadline = new Deadline(command.substring(9, command.indexOf("/by") - 1),
                                dateFormatter(command.substring(command.indexOf("/by") + 4)));
                        return new AddCommand(deadline);
                    } catch (DukeException e) {
                        System.err.println("Something went wrong: " + e.getMessage());
                        break;
                    }
                }
                case "event": {
                    try {
                        eventCheck(task, command);
                        Task event = new Event(command.substring(6, command.indexOf("/at") - 1),
                                dateFormatter(command.substring(command.indexOf("/at") + 4)));
                        return new AddCommand(event);
                    } catch (DukeException e) {
                        System.err.println("Something went wrong: " + e.getMessage());
                        break;
                    }
                }
                default: {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                }
            }
            return null;
        }
    }

}
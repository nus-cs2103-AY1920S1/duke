package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyInstructionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Contains all methods that parses user input and returns Commands that interact with TaskList and Storage to store
 * user input.
 */
public class Parser {

    /**
     * Parses a String representing user input of time and returns a LocalDateTime object representing
     * the input time.
     *
     * @param date String representing time in the format of DDDD/MM/YY HHMM.
     * @return LocalDateTime object representing input time.
     */
    public static LocalDateTime formatDate(String date) {
        String[] splitDateTime = date.split(" ", 2);
        assert splitDateTime.length == 2 : "Input needs to have both date and time";
        String[] splitDate = splitDateTime[0].split("/", 3);
        assert splitDate.length == 3: "Input should be in the format of DD/MM/YYYY";
        System.out.println(splitDate[0]);
        LocalDateTime local = LocalDateTime.of(
                Integer.parseInt(splitDate[2]),  Integer.parseInt(splitDate[1]),  Integer.parseInt(splitDate[0]),
                Integer.parseInt(splitDateTime[1].substring(0, 2)),
                Integer.parseInt(splitDateTime[1].substring(2, 4)));
        return local;

    }

    /**
     * Parses a string representing one line of user input and returns a Command object that executes the
     * necessary commands triggered by the users.
     *
     * @param input String representing a line of user input.
     * @return Command object (e.g AddCommand, DoneCommand, DeleteCommand) based on the user command
     * @throws DukeException If user input is not recognised as a valid command.
     */

    public static Command parse(String input) throws EmptyInstructionException, DukeException {
        String commandArr[] = input.split(" ", 2);
        String command = commandArr[0];
        if (command.equals("list")) {
            return new ListCommand();
        }
        if (commandArr.length == 1) {
            String message = Parser.parseException(commandArr[0]);
            throw new EmptyInstructionException(message);
        }
        return Parser.processCommand(commandArr);
    }

    public static String parseException(String command) {
        String message;
        switch (command) {
        case "done":
            message = "OOPS!!! Please enter a task number to check as done e.g done 1";
            break;
        case "todo":
            message = "OOPS!!! The description of a todo cannot be empty.";
            break;
        case "deadline":
            message = "OOPS!!! The description of a deadline cannot be empty.";
            break;
        case "event":
            message = "OOPS!!! The description of an event cannot be empty.";
            break;
        case "delete":
            message = "OOPS!!! Please enter a task number to delete e.g delete 1";
            break;
        case "find":
            message = "OOPS!!! Please enter a keyword to find!";
            break;
        case "priority":
            message = "OOPS!!! Please enter the task number that I should set a priority for!";
            break;
        default:
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return message;
    }

    public static Command processCommand (String[] arr) throws DukeException {
            String command = arr[0];
            Command resultCommand;
            switch (command) {
            case "done":
                int num = Integer.parseInt(arr[1]);
                resultCommand = new DoneCommand(num);
                break;
            case "todo":
                resultCommand = new AddCommand(new Task("T", arr[1]));
                break;
            case "deadline":
                String[] deadline = arr[1].split(" /by ", 2);
                if (deadline.length == 1) {
                    throw new DukeException("OOPS!!! Please enter a due date. e.g complete homework /by 1 Jan");
                } else {
                    resultCommand = new AddCommand(new Deadline("D", deadline[0], formatDate(deadline[1])));
                }
                break;
            case "event":
                String[] event = arr[1].split(" /at ", 2);
                if (event.length == 1) {
                    throw new DukeException("OOPS!!! Please enter an event date. e.g group meeting /at 1 Jan");
                } else {
                    resultCommand = new AddCommand(new Event("E", event[0], formatDate(event[1])));
                }
                break;
            case "delete":
                int taskNum = Integer.parseInt(arr[1]);
                resultCommand = new DeleteCommand(taskNum);
                break;
            case "find":
                String phraseToFind = arr[1];
                resultCommand = new FindCommand(phraseToFind);
                break;
            case "priority":
                String priority[] = arr[1].split(" ", 2);
                if (priority.length == 1) {
                    throw new DukeException("OOPS!! Please enter priority as \"high\", \"medium\" or \"low\"");
                }
                boolean isValid = priority[1].equals("high")
                        || priority[1].equals("medium")
                        || priority[1].equals("low");
                if (!isValid) {
                    throw new DukeException("OOPS!! Please enter priority as \"high\", \"medium\" or \"low\"");
                }
                resultCommand = new PriorityCommand(Integer.parseInt(priority[0]), priority[1]);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return resultCommand;
    }
}

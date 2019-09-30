package components;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteAllCompletedCommand;
import commands.DeleteAllTasksCommand;
import commands.DeleteCommand;
import commands.DisplayTasksCommand;
import commands.DoneTaskCommand;
import commands.DukeException;
import commands.EndSessionCommand;
import commands.FindTasksByKeywordCommand;
import commands.PrintUserInputErrorCommand;
import commands.UnrecognisedInputCommand;

/**
 * Parses all commands.
 */
public class Parser {
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Returns a date from a string.
     */
    private static Date parseAsDate(String str) {
        return dateFormatter.parse(str, new ParsePosition(0));
    }

    /**
     * The actual parsing method.
     * @param fullCommand the name of the string
     * @return a Command to execute
     * @throws DukeException thrown if keyword given is not recognised.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Scanner sc = new Scanner(fullCommand);
        String firstWord = sc.next();
        switch (firstWord) {
        case "deleteAll":
            return new DeleteAllTasksCommand();
        case "rmdone":
            return new DeleteAllCompletedCommand();
        case "find":
            try {
                String keyword = sc.nextLine();
                return new FindTasksByKeywordCommand(keyword);
            } catch (NoSuchElementException e) {
                return new PrintUserInputErrorCommand("OOPS!!! You must give me a keyword to perform a search.");
            }
        case "todo":
            try {
                String taskName = sc.nextLine();
                return new AddTodoCommand(taskName);
            } catch (NoSuchElementException e) {
                return new PrintUserInputErrorCommand("OOPS!!! The description of a todo cannot be empty.");
            }
        case "deadline":
            String deadline = "";
            String taskName = "";
            if (sc.hasNext()) {
                while (sc.hasNext()) {
                    String s = sc.next();
                    if (s.equals("/by")) {
                        deadline = sc.nextLine().strip();
                        taskName = taskName.stripTrailing();
                        break;
                    } else {
                        taskName += s + " ";
                    }
                }
                if (!deadline.isBlank()) {
                    Date date = parseAsDate(deadline);
                    return new AddDeadlineCommand(taskName, date);
                } else {
                    return new PrintUserInputErrorCommand("OOPS!!! The deadline cannot be empty.");
                }
            } else {
                return new PrintUserInputErrorCommand("OOPS!!! The description of a deadline cannot be empty.");
            }
        case "event":
            taskName = "";
            String timeFrame = "";
            if (sc.hasNext()) {
                while (sc.hasNext()) {
                    String s = sc.next();
                    if (s.equals("/at")) {
                        timeFrame = sc.nextLine().strip();
                        taskName = taskName.stripTrailing();
                        break;
                    } else {
                        taskName += s + " ";
                    }
                }
                if (!timeFrame.isBlank()) {
                    Date date = parseAsDate(timeFrame);
                    return new AddEventCommand(taskName, date);
                } else {
                    return new PrintUserInputErrorCommand("Oops!!! The time frame of an event cannot be empty.");
                }
            } else {
                return new PrintUserInputErrorCommand("Oops!!! The description of an event cannot be empty.");
            }
        case "delete":
            try {
                return new DeleteCommand(sc.nextLine());
            } catch (InputMismatchException e) {
                return new PrintUserInputErrorCommand("OOPS!!! You need to enter a natural number.");
            } catch (NoSuchElementException e) {
                return new PrintUserInputErrorCommand("You need to give me something to delete!");
            }
        case "bye":
            return new EndSessionCommand();
        case "list":
            return new DisplayTasksCommand();
        case "done":
            try {
                int index = sc.nextInt() - 1;
                return new DoneTaskCommand(index);
            } catch (InputMismatchException e) {
                return new PrintUserInputErrorCommand("OOPS!!! You need to enter a natural number.");
            }
        default:
            return new UnrecognisedInputCommand();
        }

    }
}

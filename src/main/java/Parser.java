import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Returns a date from a string.
     */
    private static Date parseAsDate(String str) {
        return dateFormatter.parse(str, new ParsePosition(0));
    }

    static Command parse(String fullCommand) {
        Scanner sc = new Scanner(fullCommand);
        String firstWord = sc.next();
        switch (firstWord) {
            case "todo":
                try {
                    String taskName = sc.nextLine();
                    return new AddTodoCommand(taskName);
                } catch (NoSuchElementException e) {
                    return new PrintUserInputErrorCommand("OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline":
                String deadline = "", taskName = "";
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
                        return new AddDeadlineCommand(taskName,date);
                    } else {
                        return new PrintUserInputErrorCommand("OOPS!!! The deadline cannot be empty.");
                    }
                } else {
                    return new PrintUserInputErrorCommand("OOPS!!! The description of a deadline cannot be empty.");
                }
            case "event":
                taskName = "";
                String timeframe = "";
                if (sc.hasNext()) {
                    while (sc.hasNext()) {
                        String s = sc.next();
                        if (s.equals("/at")) {
                            timeframe = sc.nextLine().strip();
                            taskName = taskName.stripTrailing();
                            break;
                        } else {
                            taskName += s + " ";
                        }
                    }
                    if (!timeframe.isBlank()) {
                        Date date = parseAsDate(timeframe);
                        return new AddEventCommand(taskName,date);
                    } else {
                        return new PrintUserInputErrorCommand("OOPS!!! The timeframe of an event cannot be empty.");
                    }
                } else {
                    return new PrintUserInputErrorCommand("OOPS!!! The description of an event cannot be empty.");
                }
            case "delete":
                return new DeleteCommand();
            case "bye":
                return new EndSessionCommand();
            case "list":
                return new DisplayTasksCommand();
            case "done":
                return new DoneTaskCommand();
            default:
                return new UnrecognisedInputCommand();
        }

    }
}

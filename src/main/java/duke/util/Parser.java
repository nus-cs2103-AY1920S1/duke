package duke.util;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListByPriorityCommand;
import duke.command.ListCommand;
import duke.command.UpdatePriorityCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.UnknownStorageEntryException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Parser contains the parseUserInput method which reads a command string and makes sense of its contents.
 */
public class Parser {
    /**
     * Reads the user input, makes sense of the input and return the corresponding Command which the
     * user intended.
     *
     * @param fullCommand String representation of the user input
     * @return Command derived from understanding the user input
     * @throws EmptyDescriptionException Thrown when does not include a description
     * @throws InvalidCommandException   Thrown when input string cannot be parsed
     */
    public static Command parseUserInput(String fullCommand)
            throws EmptyDescriptionException, InvalidCommandException {
        String[] strArr = fullCommand.split(" ");
        String description = "";
        switch (strArr[0]) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("prioritylist"):
            return new ListByPriorityCommand();
        case("setpriority"):
            return new UpdatePriorityCommand(
                    Integer.parseInt(strArr[1]) - 1, Integer.parseInt(strArr[2]));
        case ("done"):
            int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DoneCommand(index);
        case ("find"):
            String keyword = fullCommand.substring(6).trim();
            return new FindCommand(keyword);
        case ("todo"):
            for (int i = 1; i < strArr.length; i++) {
                description += strArr[i];
                if (i != strArr.length - 1) {
                    description += " ";
                }
            }
            return new AddTodoCommand(description);
        case ("deadline"):
            String date = "";
            int x = 0;
            for (int i = 1; i < strArr.length; i++) {
                if (!strArr[i].equals("/by")) {
                    description += strArr[i];
                    if (!strArr[i + 1].equals("/by")) {
                        description += " ";
                    }
                } else {
                    x = i;
                    break;
                }
            }
            for (int i = x + 1; i < strArr.length; i++) {
                date += strArr[i];
                if (i != strArr.length - 1) {
                    date += " ";
                }
            }
            return new AddDeadlineCommand(description, date);
        case ("event"):
            String duration = "";
            int y = 0;
            for (int i = 1; i < strArr.length; i++) {
                if (!strArr[i].equals("/at")) {
                    description += strArr[i];
                    if (!strArr[i + 1].equals("/at")) {
                        description += " ";
                    }
                } else {
                    y = i;
                    break;
                }
            }
            for (int i = y + 1; i < strArr.length; i++) {
                duration += strArr[i];
                if (i != strArr.length - 1) {
                    duration += " ";
                }
            }
            return new AddEventCommand(description, duration);
        case ("delete"):
            int i = Integer.parseInt(fullCommand.split(" ")[1]);
            return new DeleteCommand(i);
        default:
            throw new InvalidCommandException(
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Reads and makes sense of {@link Storage} input. Returns a task to be placed in
     * {@link TaskList}.
     *
     * @param info Information string provided by {@link Storage}.
     * @return {@link Task} parsed
     */
    public static Task parseStorage(String info) throws ParseException, UnknownStorageEntryException {
        String[] infoArr = info.split(Pattern.quote("|"));
        String[] dateArr;
        Task newTask;
        if (infoArr[0].trim().equals("T")) {
            newTask = new ToDo(infoArr[2].trim());
            if (infoArr[1].trim().equals("1")) {
                newTask.setDone(true);
            }
            if (infoArr.length > 3) {
                newTask.setPriority(Integer.parseInt(infoArr[3].trim()));
            }
            return newTask;
        } else if (infoArr[0].trim().equals("D")) {
            dateArr = infoArr[3].trim().split("/");
            assert Integer.parseInt(dateArr[1]) <= 12 : "Invalid Month";
            assert Integer.parseInt(dateArr[0]) <= 31 : "Invalid Day";
            assert Integer.parseInt(dateArr[2].substring(5,7)) < 24 : "Invalid Hours";
            assert Integer.parseInt(dateArr[2].substring(7,9)) < 60 : "Invalid Minutes";

            Date newDate = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.getDefault()).parse(infoArr[3].trim());
            newTask =
                    new Deadline(
                            infoArr[2].trim(),
                            newDate);
            if (infoArr[1].trim().equals("1")) {
                newTask.setDone(true);
            }
            if (infoArr.length > 4) {
                newTask.setPriority(Integer.parseInt(infoArr[4].trim()));
            }
            return newTask;
        } else if (infoArr[0].trim().equals("E")) {
            dateArr = infoArr[3].trim().split("/");
            assert Integer.parseInt(dateArr[1]) <= 12 : "Invalid Month";
            assert Integer.parseInt(dateArr[0]) <= 31 : "Invalid Day";
            assert Integer.parseInt(dateArr[2].substring(5,7)) < 24 : "Invalid Hours";
            assert Integer.parseInt(dateArr[2].substring(7,9)) < 60 : "Invalid Minutes";

            String startDate = infoArr[3].split("-")[0].trim();
            String endDate = infoArr[3].split("-")[1].trim();
            Date newDurationStart = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.getDefault()).parse(startDate);
            Date newDurationEnd = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.getDefault()).parse(endDate);
            newTask =
                    new Event(
                            infoArr[2].trim(),
                            newDurationStart,
                            newDurationEnd);
            if (infoArr[1].trim().equals("1")) {
                newTask.setDone(true);
            }
            if (infoArr.length > 4) {
                newTask.setPriority(Integer.parseInt(infoArr[4].trim()));
            }
            return newTask;
        } else {
            throw new UnknownStorageEntryException();
        }
    }

    /**
     * Parses and a date in String format and returns it in java.util.Date
     * format.
     *
     * @param date String representation of the date
     * @return java.util.Date format of the date
     */
    public static Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.getDefault()).parse(date);
    }
}

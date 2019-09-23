package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.SortCommand;
import duke.command.ListCommand;
import duke.command.ClearCommand;
import duke.command.ExitCommand;
import duke.command.JokeCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Message;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Parser class used to process Strings into data that the Command object can execute.
 */
public class Parser {

    private static Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Parses user input into a Command object containing its required components such as description, date and time
     *      according to the command given.
     * @param userInput Input provided by the user.
     * @throws DukeException Exception is thrown when there is an invalid format provided. For example,
     *      missing date / time or incorrect date / time format.
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.contains("joke")) {
            return new JokeCommand();
        }
        Scanner sc = new Scanner(userInput);
        String commandString = sc.next();

        switch (commandString) {
        case "todo":
            try {
                String desc = sc.nextLine();
                String trimmedDesc = desc.trim();
                return new TodoCommand(trimmedDesc);
            } catch (NoSuchElementException e) {
                throw new DukeException("Incomplete command. Please include a description!", e);
            }
        case "deadline":
            //fallthrough
        case "event":
            try {
                String[] descriptionAndDate = sc.nextLine().split("/", 2);
                String[] dateTimeArr = descriptionAndDate[1].split(" ");

                String desc = descriptionAndDate[0].trim();
                LocalDate date = LocalDate.parse(dateTimeArr[0], dateFormatter());
                logger.info(dateTimeArr[1]);
                String[] timeRange = dateTimeArr[1].split("-");
                LocalTime time = LocalTime.parse(timeRange[0], timeFormatter());

                if (commandString.equals("deadline")) {
                    return new DeadlineCommand(desc, date, time);
                } else {
                    LocalTime endTime = LocalTime.parse(timeRange[1], timeFormatter());
                    return new EventCommand(desc, date, time, endTime);
                }
            } catch (ArrayIndexOutOfBoundsException | NoSuchElementException | DateTimeParseException e) {
                throw new DukeException("Incomplete command, include a date and time you fool.", e);
            }
        case "delete":
            try {
                int deleteIndex = sc.nextInt();
                return new DeleteCommand(deleteIndex - 1);
            } catch (NoSuchElementException e) {
                throw new DukeException("Enter the command in this format: delete [index]", e);
            }
        case "done":
            try {
                int doneIndex = sc.nextInt();
                return new DoneCommand(doneIndex - 1);
            } catch (NoSuchElementException e) {
                throw new DukeException("Enter the command in this format: done [index]", e);
            }
        case "find":
            try {
                String keyword = sc.nextLine().trim();
                return new FindCommand(keyword);
            } catch (NoSuchElementException e) {
                throw new DukeException("Incomplete command. Input at least 1 keyword you fool!", e);
            }
        case "sort":
            try {
                String category = sc.nextLine().trim();
                return new SortCommand(category);
            } catch (NoSuchElementException e) {
                throw new DukeException("Incomplete command. Choose a category to sort by!", e);
            }
        case "list":
            return new ListCommand();
        case "clear":
            return new ClearCommand();
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException(Message.GENERAL_INVALID_INPUT, null);
        }
    }

    /**
     * Parses Strings into Task objects.
     * @param taskData String in the format type|isDone|desc|startDate(optional)|startTime(optional)|endTime(optional).
     * @return Task object.
     * @throws ParseException Exception is thrown when an invalid task is loaded.
     */
    public static Task parseStringToTask(String taskData) throws ParseException {
        Task newTask;
        String type;
        boolean isDone;
        String desc;
        LocalDate newStartDate;
        LocalTime newStartTime;
        LocalTime newEndTime;

        String[] dataArr = taskData.split("[|]");

        type = dataArr[0];
        isDone = (dataArr[1].equals("1")) ? true : false;
        desc = dataArr[2];

        switch (type) {
        case "T":
            newTask = new Todo(desc);
            break;
        case "D":
            newStartDate = LocalDate.parse((dataArr[3]), dateFormatter());
            newStartTime = LocalTime.parse((dataArr[4]), timeFormatter());

            newTask = new Deadline(desc, newStartDate, newStartTime);
            break;
        case "E":
            newStartDate = LocalDate.parse((dataArr[3]), dateFormatter());
            newStartTime = LocalTime.parse((dataArr[4]), timeFormatter());
            newEndTime = LocalTime.parse((dataArr[5]), timeFormatter());

            newTask = new Event(desc, newStartDate, newStartTime, newEndTime);
            break;
        default:
            throw new ParseException("invalid task loaded", 0);
        }

        newTask.setDone(isDone);
        return newTask;
    }

    /**
     * DateTimeFormatter which can be used to validate the formats of date strings.
     * @return DateTimeFormatter to validate date formats.
     */
    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd/MM/yy][dd-MM-yyyy]"
                + "[dd-MM-yy]", Locale.ENGLISH);
    }

    /**
     * DateTimeFormatter which can be used to validate the formats of time strings.
     * @return DateTimeFormatter to validate time formats.
     */
    public static DateTimeFormatter timeFormatter() {
        return DateTimeFormatter.ofPattern("[HHmm][HH:mm][H]", Locale.ENGLISH);
    }

}

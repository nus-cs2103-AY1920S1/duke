package duke.util;

import duke.command.*;
import duke.command.Command;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a parser object that helps to parse input from data file and user input.
 */
public class Parser {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * This method parse sa string and creates task objects base on the information.
     * @param str String in the format task type|done state|description|datetime if applicable.
     * @return Task object created.
     * @throws ParseException When the input string format is wrong.
     */
    public static Task parseTaskString(String str) throws ParseException {
        assert !str.isEmpty() : "something wrong with the data file";
        String[] strs = str.split("\\|");
        switch (strs[0]) {
        case ("ToDo"):
            Task todo = new ToDo(strs[2]);
            assert todo != null;
            if (strs[1].equals("1")) {
                todo.toggleState();
            }
            return todo;
        case ("Deadline"):
            Date date = FORMATTER.parse(strs[3]);
            Task deadline = new Deadline(strs[2], date);
            assert deadline != null;
            if (strs[1].equals("1")) {
                deadline.toggleState();
            }
            return deadline;
        case ("Event"):
            Date time = FORMATTER.parse(strs[3]);
            Task event = new Event(strs[2], time);
            assert event != null;
            if (strs[1].equals("1")) {
                event.toggleState();
            }
            return event;
        default:
            return new ToDo("shouldn't come to here");
        }
    }

    /**
     * Takes in string from user and decide what does the user wish to do. According to the
     * user's input, return the appropriate command object.
     * @param str User input.
     * @return Command object inferred from input.
     * @throws DukeException If the user input is not in the correct format
     * @throws ParseException If the date format cannot be parsed
     */
    public static Command parseUserInput(String str) throws DukeException, ParseException {
        assert !str.isEmpty() : "did not receive input";
        if (isList(str)) {
            return createListCommand();
        } else if (isDone(str)) {
            return createDoneCommand(str);
        } else if (isDelete(str)) {
            return createDeleteCommand(str);
        } else if (isFind(str)) {
            return createFindCommand(str);
        } else if (isExit(str)) {
            return createExitCommand();
        } else if (isAdd(str)) {
            return createAddCommand(str);
        } else if (isReschedule(str)) {
            return createRescheduleCommand(str);
        } else {
                throw new DukeException("☹ OOPS!!! I do not understand what did you just typed.");
        }
    }

    /**
     * Creates a DoneCommand.
     * @param str User's input
     * @return DoneCommand
     * @throws DukeException If done is not followed by a number
     */
    public static Command createDoneCommand(String str) throws DukeException {
        String[] afterSplit = str.trim().split(" ");
        if (afterSplit.length <= 1 || afterSplit[1].split(" ").length > 2) {
            throw new DukeException("☹ OOPS!!! Please input a number after done and nothing else.");
        }
        return new DoneCommand(afterSplit[1]);
    }

    /**
     * Creats a DeleteCommand.
     * @param str User's input
     * @return DeleteCommand
     * @throws DukeException If delete is not followed by a number
     */
    public static Command createDeleteCommand(String str) throws DukeException {
        String[] afterSplit = str.trim().split(" ");
        if (afterSplit.length <= 1 || afterSplit[1].split(" ").length > 2) {
            throw new DukeException("☹ OOPS!!! Please input a number after delete and nothing else.");
        }
        return new DeleteCommand(afterSplit[1]);
    }

    /**
     * Creates a ListCommand.
     * @return ListCommand
     */
    public static Command createListCommand() {
        return new ListCommand();
    }

    /**
     * Creates a FindCommand.
     * @param str User's input
     * @return FindCommand
     * @throws DukeException If a search term is not present
     */
    public static Command createFindCommand(String str) throws DukeException{
        String[] afterSplit = str.trim().split(" ", 2);
        if (afterSplit.length == 1) {
            throw new DukeException("☹ OOPS!!! Please input a search term after find.");
        }
        return new FindCommand(afterSplit[1]);
    }

    /**
     * Creates an ExitCommand.
     * @return ExitCommand
     */
    public static Command createExitCommand() {
        return new ExitCommand();
    }

    /**
     * Creates a RescheduleCommand
     * @param str User's input
     * @return RescheduleCommand
     */
    public static Command createRescheduleCommand(String str) throws ParseException {
        String[] info = str.split(" ", 3);
        Date date;
        date = FORMATTER.parse(info[2].trim());
        return new RescheduleCommand(info[1], date);
    }
    /**
     * Create AddCommand for respective task.
     * @param str The user's input
     * @return An AddCommand containing information of which task to add and content of task
     * @throws DukeException If the user did not follow the format of writing a command
     * @throws ParseException If the datetime input is not according to the format
     */
    public static Command createAddCommand(String str) throws DukeException, ParseException {
        // info splits the string into description and datetime if available
        String[] info = str.trim().split(" ", 2);

        // if info has length 1 it means that only the type of item is there but no description
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }

        if (info[0].equals("todo")) {
            return createToDoCommand(info);
        } else if (info[0].equals("deadline")) {
            return createDeadlineCommand(info);
        } else if (info[0].equals("event")) {
            return createEventCommand(info);
        } else {
            throw new DukeException("There is an unknown error parsing your message");
        }
    }

    /**
     * Create an AddCommand that add todo task to the current list of tasks
     * @param info String array that is in the form of [type of task, content of task]
     * @return An AddCommand for ToDo task.
     */
    public static Command createToDoCommand(String[] info) {
        String[] result = new String[2];
        result[0] = "todo";
        result[1] = info[1];
        return new AddCommand(result);
    }

    /**
     * Create an AddCommand that add deadline task to the current list.
     * @param info String array that is in the form of [type of task, content of task]
     * @return An AddCommand for deadline.
     * @throws DukeException If the datetime is not specified
     * @throws ParseException If the datetime is not in correct format
     */
    public static Command createDeadlineCommand(String[] info) throws DukeException, ParseException {
        String[] result = new String[2];
        Date date;
        String[] dateTimeArr = info[1].split("/by");
        // if it is a deadline event but no slash the info will be just 1 string after split
        if (dateTimeArr.length <= 1) {
            throw new DukeException("☹ OOPS!!! You need a "
                    + "/by to separate out the date time for this task.");
        }
        result[0] = "deadline";
        result[1] = dateTimeArr[0].trim();
        date = FORMATTER.parse(dateTimeArr[1].trim());
        return new AddCommand(result, date);
    }

    /**
     * Create an AddCommand that add event task to the current list.
     * @param info String array that is in the form of [type of task, content of task]
     * @return An AddCommand for event.
     * @throws DukeException If the datetime is not specified
     * @throws ParseException If the datetime is not in correct format
     */
    public static Command createEventCommand(String[] info) throws DukeException, ParseException {
        String[] result = new String[2];
        Date date;
        String[] dateTimeArr = info[1].split("/at");
        // if it is a deadline event but no slash the info will be just 1 string after split
        if (dateTimeArr.length <= 1) {
            throw new DukeException("☹ OOPS!!! You need a "
                    + "/at to separate out the date time for this task.");
        }
        result[0] = "event";
        result[1] = dateTimeArr[0].trim();
        date = FORMATTER.parse(dateTimeArr[1].trim());
        return new AddCommand(result, date);
    }

    /**
     * Checks if the command is to mark a task as done.
     * @param str The user's input
     * @return Whether the first word in the string is delete
     */
    public static boolean isDone(String str) {
        return str.trim().split(" ", 2)[0].equals("done");
    }

    /**
     * Checks if the command is to delete certain task.
     * @param str The user's input
     * @return Whether the first word in the string is delete
     */
    public static boolean isDelete(String str) {
        return str.split(" ", 2)[0].equals("delete");
    }

    /**
     * Checks if the command is to list all tasks.
     * @param str The user input
     * @return Whether the input equals list
     */
    public static boolean isList(String str) {
        return str.trim().equals("list");
    }

    /**
     * Checks if the command is to find a task.
     * @param str The user input
     * @return Wether find is the first word of the string input.
     */
    public static boolean isFind(String str) {
        return str.split(" ", 2)[0].equals("find");
    }

    /**
     * Checks if the command is to exit the program.
     * @param str The user input
     * @return Whether the input equals bye
     */
    public static boolean isExit(String str) {
        return str.trim().equals("bye");
    }

    /**
     * Checks if the command is to add a task.
     * @param str The user input
     * @return Whether the input contains todo, deadline or event
     */
    public static boolean isAdd(String str) {
        String commandWord = str.trim().split(" ", 2)[0];
        return commandWord.equals("todo") || commandWord.equals("deadline") || commandWord.equals("event");
    }

    /**
     * Checks if the command is to reschedule a task
     * @param str The user's input
     * @return Whether the input's first word is reschedule
     */
    public static boolean isReschedule(String str) {
        String commandWord = str.trim().split(" ", 2)[0];
        return commandWord.equals("reschedule");
    }
}

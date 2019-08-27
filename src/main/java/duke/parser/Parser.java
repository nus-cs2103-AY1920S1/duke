package duke.parser;

import duke.commands.*;
import duke.tasks.*;
import java.lang.IllegalArgumentException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Parser breaks down input string into relevant data structures to be used.
 * Parser detects and reports any syntax errors.
 */
public class Parser {
    private static final String emptyToDoErrorMessage = "____________________________________________________________\n"
            + "\u2639 OOPS!!! The description of a todo cannot be empty.\n"
            + "____________________________________________________________";

    /**
     * Parses full command string and outputs relevant command.
     * @param fullCommand
     * @return Command
     * @throws IllegalArgumentException
     * @throws ParseException
     */
    public static Command parse(String fullCommand) throws IllegalArgumentException, ParseException {
        String task;
        int i;
        Date date;
        String format = "dd/MM/yyyy HHmm";
        SimpleDateFormat readFormat = new SimpleDateFormat(format);
        ;
        Command command;

        String[] arr = fullCommand.split(" ", 2);
        String commandType = arr[0];

        switch (commandType) {
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ByeCommand();
            break;
        case "todo":
            if (arr.length == 2) {
                command = new ToDoCommand(new ToDo(arr[1]));
            } else {
                throw new IllegalArgumentException(emptyToDoErrorMessage);
            }
            break;
        case "deadline":
            task = arr[1].split("/by")[0].trim();
            date = readFormat.parse(arr[1].split("/by")[1].trim());
            command = new DeadlineCommand(new Deadline(task, date));
            break;
        case "event":
            task = arr[1].split("/at")[0].trim();
            date = readFormat.parse(arr[1].split("/at")[1].trim());
            command = new EventCommand(new Event(task, date));
            break;
        case "done":
            i = Integer.parseInt(arr[1]);
            command = new DoneCommand(i);
            break;
        case "delete":
            i = Integer.parseInt(arr[1]);
            command = new DeleteCommand(i);
            break;
        default:
            command = new IllegalCommand();
        }
        return command;
    }

    /**
     * Parses full task string and returns a task instance.
     * @param fullTask
     * @return task
     * @throws ParseException
     */
    //TODO: task initialised hard coded
    public static Task parseTask(String fullTask) throws ParseException{
        Task t = new Task("");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        char taskType = fullTask.charAt(3);
        boolean done = (fullTask.charAt(6) + "").equals("\u2713");
        String task, dateString;
        Date date;
        switch (taskType) {
        case 'T':
            task = fullTask.split(" ", 2)[1];
            t = new ToDo(task, done);
            break;
        case 'D':
            task = fullTask.split(" ", 2)[1].split("\\(by:")[0].trim();
            dateString = fullTask.split(" ", 2)[1].split("\\(by:")[1].trim().replace(")", "");
            date = sdf.parse(dateString); // remove trailing bracket
            t = new Deadline(task, date);
            break;
        case 'E':
            task = fullTask.split(" ", 2)[1].split("\\(at:")[0].trim();
            dateString = fullTask.split(" ", 2)[1].split("\\(at:")[1].trim().replace(")", "");
            date = sdf.parse(dateString); // remove trailing bracket
            t = new Event(task, date);
        }
        return t;
    }
}
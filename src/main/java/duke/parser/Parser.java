package duke.parser;

import duke.commands.FindCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.IllegalCommand;
import duke.commands.ToDoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.NoteCommand;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Note;

import duke.ui.UI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parser breaks down input string into relevant data structures to be used.
 * Parser detects and reports any syntax errors.
 */
public class Parser {
    private static final String EMPTY_TODO_ERROR_MESSAGE = "\u2639 OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_NOTE_ERROR_MESSAGE = "\u2639 OOPS!!! The description of a note cannot be empty.";

    /**
     * Parses full command string and outputs relevant command.
     *
     * @param fullCommand Full Command
     * @return Command
     * @throws IllegalArgumentException Command is not valid
     * @throws ParseException Parsing error
     */
    public static Command parse(String fullCommand) throws IllegalArgumentException, ParseException {
        String task;
        int i;
        Date date;
        String format = "dd/MM/yyyy HHmm";
        SimpleDateFormat readFormat = new SimpleDateFormat(format);
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
            if (arr.length != 2) {
                throw new IllegalArgumentException(EMPTY_TODO_ERROR_MESSAGE);
            }

            command = new ToDoCommand(new ToDo(arr[1]));
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
        case "note":
            if (arr.length != 2) {
                throw new IllegalArgumentException(EMPTY_NOTE_ERROR_MESSAGE);
            }

            command = new NoteCommand(new Note(arr[1]));
            break;
        case "done":
            i = Integer.parseInt(arr[1]);
            command = new DoneCommand(i);
            break;
        case "delete":
            i = Integer.parseInt(arr[1]);
            command = new DeleteCommand(i);
            break;
        case "find":
            command = new FindCommand(arr[1]);
            break;
        default:
            command = new IllegalCommand();
        }
        return command;
    }

    /**
     * Parses full task string and returns a task instance.
     *
     * @param fullTask Full Task
     * @return task
     * @throws ParseException Parsing Error
     */
    //TODO: task initialised hard coded!
    public static Task parseTask(String fullTask) throws ParseException {
        Task t;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        char taskType = fullTask.charAt(3);
        boolean done = (fullTask.charAt(6) + "").equals("\u2713");
        String task;
        String dateString;
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
            break;
        case 'N':
            task = fullTask.split(" ", 2)[1];
            t = new Note(task);
            break;
        default:
            t = new Task("");
        }
        return t;
    }
}
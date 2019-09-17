package task;

import exceptions.DukeException;
import utilities.Storage;
import utilities.TaskList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * contains actions for event task.
 */
public class Event extends Task {
    private String midCommand;
    private String formattedDate;

    /**
     * constructor for child class.
     *
     * @param command is the user input string
     *
     * @throws IllegalArgumentException in case date is not entered in the correct format
     */
    private Event(String command) throws IllegalArgumentException {
        super(command);
        this.done = false;
        String[]splitUpDate = command.split("/",2);

        try {
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formalDate = ft.parse(splitUpDate[1].substring(3));

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            formattedDate = dateFormat.format(formalDate);

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        midCommand = splitUpDate[0];
    }

    /**
     * to print for "list" command.
     *
     * @return string in the format required
     */
    public String printer() {
        if (done) {
            return "[E][✓] " + midCommand + "(at: " + formattedDate + ")";
        } else {
            return "[E][✗] " + midCommand + "(at: " + formattedDate + ")";
        }
    }

    /**
     * to print for text file.
     *
     * @return string in the format required
     */
    public String printToOutput() {
        if (done) {
            return "E | 1 | " + midCommand + " | " + formattedDate;
        } else {
            return "E | 0 | " + midCommand + " | " + formattedDate;
        }
    }

    /**
     * read user input as convert it into a Task.
     *
     * @param s is the user input string
     *
     * @return a Task.Task (Task.Event) object
     */
    public static Task outputAsEvent(String s) {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2].trim() + " /at: " + segments[3].trim();
        Event newTask = new Event(taskCommand);

        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }

        return newTask;
    }

    /**
     * Creates a new event task.
     *
     * @param command is the user string input to be processed
     *
     * @throws DukeException in case user inputs in an incorrect format
     */
    public static void createEvent(String command, TaskList tasks, Storage storage) throws DukeException {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        try {
            if (midCommand.length() != 0) {
                tasks.add(new Event(midCommand));
                storage.updateFile(tasks);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    /**
     * marks if task is done.
     */
    public void taskDone() {
        done = true;
    }
}

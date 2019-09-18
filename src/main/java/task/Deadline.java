package task;

import exceptions.DukeException;
import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * contains actions for deadline task.
 */
public class Deadline extends Task {

    private String midCommand;
    private String formattedDate;

    /**
     * constructor for child class.
     *
     * @param command is the user input string
     *
     * @throws IllegalArgumentException in case date is not entered in the correct format
     */
    public Deadline(String command) throws IllegalArgumentException {
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
            return "[D][:)] " + midCommand + "(by: " + formattedDate + ")";
        } else {
            return "[D][:(] " + midCommand + "(by: " + formattedDate + ")";
        }
    }

    /**
     * to print for text file.
     *
     * @return string in the format required
     */
    public String printToOutput() {
        if (done) {
            return "D | 1 | " + midCommand + " | " + formattedDate;
        } else {
            return "D | 0 | " + midCommand + " | " + formattedDate;
        }
    }

    /**
     * read user input as convert it into a Task.
     *
     * @param s is the user input string
     *
     * @return a Task.Task (Task.Deadline) object
     */
    public static Task outputAsDeadline(String s) {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2].trim() + " /by: " + segments[3].trim();
        Deadline newTask = new Deadline(taskCommand);

        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }

        return newTask;
    }

    /**
     * Creates a new deadline task.
     *
     * @param command is the user string input to be processed
     *
     * @throws DukeException in case user inputs in an incorrect format
     */
    public static void createDeadline(String command, TaskList tasks, Storage storage, ExpenseList expenses) throws DukeException {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        try {
            if (midCommand.length() != 0) {
                tasks.add(new Deadline(midCommand));
                storage.updateFile(tasks, expenses);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    /**
     * marks when task is done.
     */
    public void taskDone() {
        done = true;
    }
}


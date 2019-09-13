package Task;

import Exceptions.DukeException;
import Utilities.Storage;
import Utilities.TaskList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * contains actions for event task
 */
public class Event extends Task {
    /**
     * midCommand is attribute for the string command after "deadline"
     * formattedDate is attribute for the date
     */
    private String midCommand;
    private String formattedDate;

    /**
     * constructor for child class
     * defines the midCommand, and calculates the formattedDate
     * @param command is the user input string
     * @throws DukeException in case date is not entered in the correct format
     */
    public Event(String command) throws IllegalArgumentException {
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
     * to print for "list" command
     * @return string in the format required
     */
    public String printer(){
            if (done) {
                String result = "[E][✓] " + midCommand + "(at: " + formattedDate + ")";
                return result;
            } else {
                String result = "[E][✗] " + midCommand + "(at: " + formattedDate + ")";
                return result;
            }
    }

    /**
     * to print for text file
     * @return string in the format required
     */
    public String printToOutput(){
        if (done) {
            String result = "E | 1 | " + midCommand + " | " + formattedDate;
            return result;
        } else {
            String result = "E | 0 | " + midCommand + " | " + formattedDate;
            return result;
        }
    }

    /**
     * read user input as convert it into a Task.Task
     * @param s is the user input string
     * @return a Task.Task (Task.Event) object
     * @throws DukeException in case segments[3] is not in the proper date format
     */
    public static Task outputAsEvent(String s) throws DukeException {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2].trim() + " /at: " + segments[3].trim();
        Event newTask = new Event(taskCommand);

        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        } else {}

        return newTask;
    }

    /**
     * Creates a new event task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
    public static void createEvent(String command, TaskList tasks, Storage storage) throws DukeException {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        try{
            if (midCommand.length() != 0) {
                tasks.add(new Event(midCommand));
                storage.updateFile(tasks);
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new DukeException("");
        }
    }

    /**
     * marks if task is done
     */
    public void taskDone(){
        done = true;
    }
}

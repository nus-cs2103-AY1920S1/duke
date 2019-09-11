import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * contains actions for deadline task
 */
public class Deadline extends Task {

    /**
     * midCommand is attribute for the string command after "deadline"
     * formattedDate is attribute for the date
     */
    private String midCommand;
    private String formattedDate;

    /**
     * constructor for child class
     * defines the midcommand, and calculates the formattedDate
     * @param command is the user input string
     * @throws DukeException in case date is not entered in the correct format
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
     * to print for "list" command
     * @return string in the format required
     */
    public String printer(){
            if (done) {
                String result = "[D][✓] " + midCommand + "(by: " + formattedDate + ")";
                return result;
            } else {
                String result = "[D][✗] " + midCommand + "(by: " + formattedDate + ")";
                return result;
            }
    }

    /**
     * to print for text file
     * @return string in the format required
     */
    public String printToOutput(){
            if (done) {
                String result = "D | 1 | " + midCommand + " | " + formattedDate;
                return result;
            } else {
                String result = "D | 0 | " + midCommand + " | " + formattedDate;
                return result;
            }
    }

    /**
     * read user input as convert it into a Task
     * @param s is the user input string
     * @return a Task (Deadline) object
     * @throws DukeException in case segments[3] is not in the proper date format
     */
    public static Task outputAsDeadline(String s) throws DukeException {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2] + "/by: " + segments[3];
        Deadline newTask = new Deadline(taskCommand);

        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        } else {}

        return newTask;
    }

    /**
     * Creates a new deadline task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
    public static void createDeadline(String command, TaskList tasks, Storage storage) throws DukeException {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        try{
            if (midCommand.length() != 0) {
                tasks.add(new Deadline(midCommand));
                storage.updateFile(tasks);
            } else {
                throw new Exception();
            }
        } catch(Exception e){
            throw new DukeException("");
        }
    }

    /**
     * marks when task is done
     */
    public void taskDone(){
        done = true;
    }
}


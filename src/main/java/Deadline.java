import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    /**
     * midcommand is attribute for the string command after "deadline"
     * formattedDate is attribute for the date
     */
    private String midcommand; private String formattedDate;

    /**
     * constructor for child class
     * defines the midcommand, and calculates the formattedDate
     * @param command is the user input string
     * @throws DukeException in case date is not entered in the correct format
     */
    public Deadline(String command) throws DukeException {
        super(command);
        this.done = false;
        String[]splitteddate = command.split("/",2);

        try {
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formalDate = ft.parse(splitteddate[1].substring(3));

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            formattedDate = dateFormat.format(formalDate);

        } catch(Exception e) {
            throw new DukeException("");
        }

        midcommand = splitteddate[0];
    }

    /**
     * to print for "list" command
     * @return string in the format required
     */
    public String printer(){
            if(done){
                String result = "[D][✓] " + midcommand + "(by: " + formattedDate + ")";
                return result;
            }else{
                String result = "[D][✗] " + midcommand + "(by: " + formattedDate + ")";
                return result;
            }
    }

    /**
     * to print for text file
     * @return string in the format required
     */
    public String printToOutput(){
            if(done){
                String result = "D | 1 | " + midcommand + " | " + formattedDate;
                return result;
            }else{
                String result = "D | 0 | " + midcommand + " | " + formattedDate;
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
        }else{}
        return newTask;
    }

    /**
     * marks when task is done
     */
    public void taskDone(){
        done = true;
    }
}


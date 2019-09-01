import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    /**
     * midcommand is attribute for the string command after "deadline"
     * formattedDate is attribute for the date
     */
    private String midcommand;
    private String formattedDate;

    /**
     * constructor for child class
     * defines the midcommand, and calculates the formattedDate
     * @param command is the user input string
     * @throws DukeException in case date is not entered in the correct format
     */
    public Event(String command) throws DukeException {
        super(command);
        this.done = false;
        String[]splitteddate = command.split("/",2);

        try {
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formalDate = ft.parse(splitteddate[1].substring(3));

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            formattedDate = dateFormat.format(formalDate);

        }catch(Exception e){
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
                String result = "[E][✓] " + midcommand + "(at: " + formattedDate + ")";
                return result;
            }else{
                String result = "[E][✗] " + midcommand + "(at: " + formattedDate + ")";
                return result;
            }
    }

    /**
     * to print for text file
     * @return string in the format required
     */
    public String printToOutput(){
        if(done){
            String result = "E | 1 | " + midcommand + " | " + formattedDate;
            return result;
        }else{
            String result = "E | 0 | " + midcommand + " | " + formattedDate;
            return result;
        }
    }

    /**
     * read user input as convert it into a Task
     * @param s is the user input string
     * @return a Task (Event) object
     * @throws DukeException in case segments[3] is not in the proper date format
     */
    public static Task outputAsEvent(String s) throws DukeException {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2] + "/at: " + segments[3];
        Event newTask = new Event(taskCommand);
        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }else{}
        return newTask;
    }

    /**
     * marks if task is done
     */
    public void taskDone(){
        done = true;
    }
}

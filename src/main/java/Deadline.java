import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Deadline extends Task {

    private String midcommand; private String informalDate; private String formattedDate;

    public Deadline(String command) throws ParseException, DukeException {
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

    public String printer(){
            if(done){
                String result = "[D][✓] " + midcommand + "(by: " + formattedDate + ")";
                return result;
            }else{
                String result = "[D][✗] " + midcommand + "(by: " + formattedDate + ")";
                return result;
            }
    }

    public String printToOutput(){
            if(done){
                String result = "D | 1 | " + midcommand + " | " + formattedDate;
                return result;
            }else{
                String result = "D | 0 | " + midcommand + " | " + formattedDate;
                return result;
            }
    }

    public static Task outputAsDeadline(String s) throws ParseException, DukeException {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2] + "/by: " + segments[3];
        Deadline newTask = new Deadline(taskCommand);
        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }else{}
        return newTask;
    }

    public void taskDone(){
        done = true;
    }
}


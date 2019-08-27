import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private String midcommand;
    private String formattedDate;

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

    public String printer(){
            if(done){
                String result = "[E][✓] " + midcommand + "(at: " + formattedDate + ")";
                return result;
            }else{
                String result = "[E][✗] " + midcommand + "(at: " + formattedDate + ")";
                return result;
            }
    }

    public String printToOutput(){
        if(done){
            String result = "E | 1 | " + midcommand + " | " + formattedDate;
            return result;
        }else{
            String result = "E | 0 | " + midcommand + " | " + formattedDate;
            return result;
        }
    }

    public static Task outputAsEvent(String s) throws DukeException {
        String[]segments = s.split("\\|");
        String taskCommand = segments[2] + "/at: " + segments[3];
        Event newTask = new Event(taskCommand);
        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }else{}
        return newTask;
    }

    public void taskDone(){
        done = true;
    }
}

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Deadline extends Task {

    private String midcommand; private String informalDate; private Date formalDate; private boolean isTimeDefined;

    public Deadline(String command) throws ParseException {
        super(command);
        this.done = false;
        String[]splitteddate = command.split("/",2);

        try {
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HHmm");
            formalDate = ft.parse(splitteddate[1].substring(3));
            //System.out.println(formalDate.toString());
            isTimeDefined = true;
        }catch(Exception e){
            informalDate = splitteddate[1].substring(3);
            isTimeDefined = false;
        }

        midcommand = splitteddate[0];
    }

    public String printer(){
        if(isTimeDefined){
            //DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            String formattedDate = dateFormat.format(formalDate);
            if(done){
                String result = "[D][✓] " + midcommand + "(by: " + formattedDate + ")";
                return result;
            }else{
                String result = "[D][✗] " + midcommand + "(by: " + formattedDate + ")";
                return result;
            }
        }else{
            if(done){
                String result = "[D][✓] " + midcommand + "(by: " + informalDate.substring(3) + ")";
                return result;
            }else{
                String result = "[D][✗] " + midcommand + "(by: " + informalDate.substring(3) + ")";
                return result;
            }
        }
    }

    public void taskDone(){
        done = true;
    }
}


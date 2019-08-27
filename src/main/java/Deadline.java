import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    String endDate;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    Date date;
    Deadline(String taskName, String endDate){
        super(taskName);
        this.endDate = endDate;
        try {
            date = format.parse(endDate);
        } catch( Exception e ){
            System.out.println("Invalid date format");
        }
    }

    public void formatDate(){

    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getTaskDetails(){
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        if (date == null) {
            return "[D]" + "[" + doneSymbol + "] " + getTaskName() + " (by: " + endDate + ")";
        } else {
            return "[D]" + "[" + doneSymbol + "] " + getTaskName() + " (by: " + date + ")";
        }
    }
}

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event to be completed
 * Contains a description of the task
 * Contains the time of the event
 */


public class Event extends Task {
    protected String time;
    protected Date date;

    public Event (String descriptionAndTime){
        super(descriptionAndTime);
        String[] splitString = descriptionAndTime.split("/at");
        this.description = splitString[0].substring(6, splitString[0].length() - 1);
        this.time = splitString[1].substring(1, splitString[1].length());
        try {
            understandDate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Determines date from time input if format fits "DD/MM/YYYY HHMM"
     *
     */
    public void understandDate() throws Exception{
        String[] splitString = time.split(" ");
        if (splitString.length == 2) {
            if (splitString[1].length() == 4) {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(splitString[0]);
                long hour = Long.parseLong(splitString[1].substring(0,2));
                long min = Long.parseLong(splitString[1].substring(2,4));
                date.setTime(date.getTime() + ((hour * 60 + min)*60000));
                this.time = date.toString();
            }
        }
    }

    @Override
    public String toString() {
        String output = "[E][" + this.getStatusIcon() + "] " + this.description;
        output += " (at: " + this.time + ")";
        return output;
    }
}

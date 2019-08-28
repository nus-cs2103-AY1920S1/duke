import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Deadline represents a deadline with a description and date
 */
public class Deadline extends Task{
    private String date;
    private Date dateTime;

    /**
     * Create a deadline with a description and date
     * @param name description
     * @param date date
     */
    Deadline(String name, String date){
        super(name);
        try{
            dateTime = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        }catch (ParseException e){
            // e.printStackTrace();
            this.date = date;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D][" + getState() + "] " + getName() + " (by: " + (date != null ? date : new SimpleDateFormat("MMM dd yyyy',' hh:mmaa").format(dateTime)) + ")";
    }
}

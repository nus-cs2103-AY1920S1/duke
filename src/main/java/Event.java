import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class representing an event
 */
public class Event extends Task{
    private String date;
    private Date dateTime;

    /**
     * Create an event with description and date
     * @param name description
     * @param date date
     */
    Event(String name, String date){
        super(name);
        try{
            dateTime = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        }catch (ParseException e){
            e.printStackTrace();
            this.date = date;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E][" + getState() + "] " + getName() + " (at: " + (date != null ? date : new SimpleDateFormat("MMM dd yyyy',' hh:mmaa").format(dateTime)) + ")";
    }
}

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Event class represents one of the 3 Tasks.
 */
public class Event extends Task {

    private String at;
    private Date dateAndTime;
    private SimpleDateFormat dateFormat;

    /**
     * Constructor of Event object.
     * @param description task description.
     * @param dateAndTime dateAndTime in which event is going to take place.
     * @throws IllegalArgumentException Invalid dateAndTime.
     * @throws ParseException invalid dateAndTime.
     */
    public Event(String description, String dateAndTime) throws IllegalArgumentException, ParseException {
        super(description);
        this.at = dateAndTime;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            dateFormat.setLenient(false);
            this.dateAndTime = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * Returns Date representation of the dateAndTime.
     * @return dateAndTime.
     */
    public Date getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Returns string representation of dateAndTime.
     * @return at.
     */
    public String getAt() {
       return at;
    }

    /**
     * Returns string representation of Event object.
     * @return string represenation of Event object.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), dateFormat.format(dateAndTime));
    }

    /*public static Event outputAsEvent(String lineToRead) {
        String[] descriptionNAt = lineToRead.split(",");
        String desc = descriptionNAt[0];
        String at = descriptionNAt[1];
        System.out.println(desc);
        System.out.println(at);
        return new Event(desc, at);
    }*/

    /**
     * Returns description of Event task.
     * @return description.
     */
    public String getDescription() {
        return super.getDescription();
    }
}
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Event extends Task {

    private String at;
    private Date dateAndTime;
    private SimpleDateFormat dateFormat;

    public Event(String description, String dateAndTime) throws IllegalArgumentException, ParseException {
        super(description);
        this.at = dateAndTime;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dateFormat.setLenient(false);
            this.dateAndTime = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {
            throw e;
        }
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }
    public String getAt() {
       return at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", getDescription(), dateFormat.format(dateAndTime));
    }

    /*public static Event outputAsEvent(String lineToRead) {
        String[] descriptionNAt = lineToRead.split(",");
        String desc = descriptionNAt[0];
        String at = descriptionNAt[1];
        System.out.println(desc);
        System.out.println(at);
        return new Event(desc, at);
    }*/

    public String getDescription() {
        return super.getDescription();
    }
}

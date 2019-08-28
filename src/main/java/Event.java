import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Event extends Task{
    protected String at;

    public Event(String description, String dateTime) {
        super(description);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.at = dateFormat.parse(dateTime).toString();
        } catch (ParseException pe) {
            System.out.println("    Next time, please specify the deadline in this format: dd/mm/yyy hh:mm");
            System.out.println("    example: 01/12/2019 14:30");
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}

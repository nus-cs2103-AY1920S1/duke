import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String on;
    protected Date date;
    protected Date startTime;
    protected Date endTime;

    public Event(String description, String on) {
        super(description);
        this.on = on;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse(on.substring(0, 9));
            formatter = new SimpleDateFormat("HHmm");
            this.startTime = formatter.parse(on.substring(10, 14));
            this.endTime = formatter.parse(on.substring(15));
        } catch (ParseException pe) {
            System.out.println("Something serious happened here...");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.on + ")";
    }

    @Override
    public String saveString() {
        int done = this.getDone() ? 1 : 0;
        return "E" + super.saveString() + " | " + this.on + "\n";
    }
}

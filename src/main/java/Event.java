import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task{
    
    protected Date at;
    protected SimpleDateFormat format = new SimpleDateFormat();
    
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }
    
    @Override
    public String toString() {
        format = new SimpleDateFormat("EEEE, MMM d, HH:mm");
        return "[E]" + super.toString() + "(at: " + format.format(at) + ")";
    }
}


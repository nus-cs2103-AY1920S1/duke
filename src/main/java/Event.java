import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String showTask() {
        return "[E]" + super.showTask() + " (at: " + at.toString() + ")";
    }
    
    public String toSave() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
    	String atString = sdf.format(at);
    	if (super.isDone) {
    		return "doneevent " + super.description + " /at " + atString;
    	} else {
    		return "event " + super.description + " /at " + atString;
    	}
    }
}

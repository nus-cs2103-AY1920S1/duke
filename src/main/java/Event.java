/**
 * @author bakwxh
 * @version 0.1
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
	protected Date at;

    /**
     * Constructor.
     * @param description Description.
     * @param at At time.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Shows task.
     */
    @Override
    public String showTask() {
        return "[E]" + super.showTask() + " (at: " + at.toString() + ")";
    }
    
    /**
     * Shows task as its saving format.
     */
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

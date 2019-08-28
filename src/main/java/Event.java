public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String showTask() {
        return "[E]" + super.showTask() + " (at: " + at + ")";
    }
    
    public String toSave() {
    	if (super.isDone) {
    		return "doneevent " + super.description + " /at " + at;
    	} else {
    		return "event " + super.description + " /at " + at;
    	}
    }
}

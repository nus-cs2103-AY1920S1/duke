public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String showTask() {
        return "[D]" + super.showTask() + " (by: " + by + ")";
    }

    @Override
    public String toSave() {
    	if (super.isDone) {
    		return "donedeadline " + super.description + " /by " + by;
    	} else {
    		return "deadline " + super.description + " /by " + by;
    	}
    }
}

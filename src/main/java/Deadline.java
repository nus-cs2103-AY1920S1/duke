import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String showTask() {
        return "[D]" + super.showTask() + " (by: " + by.toString() + ")";
    }

    @Override
    public String toSave() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
    	String byString = sdf.format(by);
    	if (super.isDone) {
    		return "donedeadline " + super.description + " /by " + byString;
    	} else {
    		return "deadline " + super.description + " /by " + byString;
    	}
    }
}

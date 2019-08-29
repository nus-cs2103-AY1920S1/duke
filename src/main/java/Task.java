import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }
    
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    
    public String toStringDone() {
        return "Nice, I've marked this item as done\n\t\t[" + this.getStatusIcon() + "] " + this.description;
    }

    //...
}

class Deadline extends Task {

    protected Date by;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        SimpleDateFormat date =new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date1=date.parse(by);
        this.by = date1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}

class Event extends Task {

    protected Date at;

    public Event(String description, String at) throws ParseException {
        super(description);
        SimpleDateFormat date =new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date1=date.parse(at);
        this.at = date1;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

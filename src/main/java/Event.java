import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date on;


    public Event(String description, Date on, boolean b){
        super(description);
        this.on = on;
        this.isDone = b;
    }
    public Event(String description, Date on){
        super(description);
        this.on = on;
    }

    @Override
    public String toString(){
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + df.format(on) + ")";
    }

}

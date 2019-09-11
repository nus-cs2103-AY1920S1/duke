package Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date at;
    private int duration;
    public Event(String description, String at) throws ParseException {
        super(description);
        Date date=new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
        this.at = date;
        this.duration = 4;
    }

    public Date getAt() {
        return this.at;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}

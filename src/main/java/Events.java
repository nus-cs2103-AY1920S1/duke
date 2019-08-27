import java.text.SimpleDateFormat;
import java.util.Date;

public class Events extends Task {
    String time;
    Date date;

    public Events(String name, SimpleDateFormat formatter, Date date) {
        super(name, formatter);
        this.date = date;
        this.type = "[E]";
    }

    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[✓] " + this.name + "(at:" + formatter.format(this.date) + ")";
        } else {
            output = this.type + "[X] " + this.name + "(at:" + formatter.format(this.date) + ")";
        }
        return output;
    }
}

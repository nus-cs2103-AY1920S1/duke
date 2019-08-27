import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadlines extends Task {

    String submitBy;
    Date date;

    public Deadlines(String name, SimpleDateFormat formatter, Date date) {
        super(name, formatter);
        this.type = "[D]";
        this.submitBy = submitBy;
        this.date = date;
    }

    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[✓] " + this.name + "(by:" + formatter.format(this.date) + ")";
        } else {
            output = this.type + "[X] " + this.name + "(by:" + formatter.format(this.date) + ")";
        }
        return output;
    }
}


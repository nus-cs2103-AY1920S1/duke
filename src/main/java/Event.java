import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date startPeriod;
    private Date endPeriod;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    Event(String description, String period) throws ParseException {
        super(description);
        String[] periods = period.split("-");
        this.startPeriod = this.formatter.parse(periods[0]);
        this.endPeriod = this.formatter.parse(periods[1]);
    }

    Event(String description, Date startPeriod, Date endPeriod) {
        super(description);
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    @Override
    String saveFormat() {
        return "E|" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s - %s )", startPeriod, endPeriod);
    }
}

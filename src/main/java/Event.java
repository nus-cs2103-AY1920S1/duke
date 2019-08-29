import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Event extends Task {
    Date time;

    public Event(String content, String time) {
        super(content);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = new Date();
        try {
            date = formatter.parse(time);
            System.out.println(date);
            System.out.println(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.time = date;
    }

    @Override
    public String getTime() {
        return inputFormatter.format(time);
    }

    @Override
    public String toString() {
        return done ? String.format("[E][%c] %s (by: %s)", tick, content, outputFormatter.format(time))
                : String.format("[E][%c] %s (by: %s)", cross, content, outputFormatter.format(time));
    }
}

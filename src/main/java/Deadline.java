import java.text.ParseException;
import java.util.Date;

class Deadline extends Task {
    Date deadline;

    public Deadline(String content, String deadline) {
        super(content);
        Date date = new Date();
        try {
            date = inputFormatter.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.deadline = date;
    }

    @Override
    public String getTime() {
        return inputFormatter.format(deadline);
    }

    @Override
    public String toString() {
        return done ? String.format("[D][%c] %s (by: %s)", tick, content, outputFormatter.format(deadline))
                : String.format("[D][%c] %s (by: %s)", cross, content, outputFormatter.format(deadline));
    }
}
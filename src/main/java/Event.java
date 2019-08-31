import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        try {
            this.at = parseDateTime(at);
        } catch (NumberFormatException ex) {
            Ui.showError("Invalid format! Try dd/mm/yyyy hhmm instead!");
        } catch (DateTimeException ex) {
            Ui.showError(ex.getMessage());
        }
    }
    private LocalDateTime parseDateTime(String at) throws DateTimeException, NumberFormatException {
        try {
            String[] splited = at.split(" ");
            String[] dateFields = splited[0].split("/");
            int hour = Integer.parseInt(splited[1].substring(0, 2));
            int minute = Integer.parseInt(splited[1].substring(2));
            return LocalDateTime.of(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]), hour, minute);
        } catch (DateTimeException | NumberFormatException ex) {
            throw ex;
        }
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = parseDateTime(at);
    }

    public String getStringAt() {
        return at.getDayOfMonth() + "/" + at.getMonthValue() + "/" + at.getYear() + " " + String.format("%02d", at.getHour()) + String.format("%02d", at.getMinute());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getStringAt() + ")";
    }
}
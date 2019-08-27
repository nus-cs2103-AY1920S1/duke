import java.time.LocalDateTime;

public class Event extends Task {
    private String eventDateString;
    private LocalDateTime eventDate;

    public Event(String description, String eventDate) {
        super(description);

        StringBuilder temp = new StringBuilder();
        String[] eventDateArr = eventDate.split(" ");

        temp.append(eventDateArr[0]);
        temp.append(":");

        for (int i = 1; i < eventDateArr.length; i++) {
            temp.append(" ");
            temp.append(eventDateArr[i]);
        }

        this.eventDateString = temp.toString();
        this.eventDate = storeAsDateTime(eventDateString);
    }

    @Override
    public String toString() {
        String task = "[E][" + this.getStatusIcon() + "] " + this.description + " (" + eventDateString + ")";
        return task;
    }
}
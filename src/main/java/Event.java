public class Event extends Task {
    private String eventDate;

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

        this.eventDate = temp.toString();
    }

    @Override
    public String toString() {
        String task = "[E][" + this.getStatusIcon() + "] " + this.description + " (" + eventDate + ")";
        return task;
    }
}
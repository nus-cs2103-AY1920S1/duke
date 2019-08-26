public class Event extends Task {
    private String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = makeEventDate(eventDate);
    }

    public Event(String description, String eventDate, boolean status) {
        super(description);
        this.eventDate = makeEventDate(eventDate);
        this.isDone = status;
    }

    public String makeEventDate(String eventDate) {
        StringBuilder temp = new StringBuilder();
        String[] eventDateArr = eventDate.split(" ");

        temp.append(eventDateArr[0]);

        if (!eventDateArr[0].contains(":")) {
            temp.append(":");
        }

        for (int i = 1; i < eventDateArr.length; i++) {
            temp.append(" ");
            temp.append(eventDateArr[i]);
        }

        return temp.toString();
    }

    @Override
    public String toFileFormat() {
        StringBuilder fileFormat = new StringBuilder();

        fileFormat.append("E~");

        if (this.isDone) {
            fileFormat.append("1~");
        } else {
            fileFormat.append("0~");
        }

        fileFormat.append(this.description);
        fileFormat.append("~");
        fileFormat.append(this.eventDate);

        return fileFormat.toString();
    }

    @Override
    public String toString() {
        String task = "[E][" + this.getStatusIcon() + "] " + this.description + " (" + eventDate + ")";
        return task;
    }
}
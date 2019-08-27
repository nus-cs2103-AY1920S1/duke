public class Event extends Task {

    protected Date date;

    public Event(String description, String at) {
        super(description);
        String[] dateAndTime = new String[2];
        dateAndTime = at.split(" ");
        String[] date = new String[3];
        date = dateAndTime[0].split("/");
        this.date = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                Integer.parseInt(date[2]), Integer.parseInt(dateAndTime[1]));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}

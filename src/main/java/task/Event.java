package task;

public class Event extends Task {

    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    /**
     * Event constructor.
     * @param status 0 or 1
     * @param desc description
     * @param date date
     */
    public Event(int status, String desc, String date) {
        super(desc);
        this.date = date;
        isDone = (status == 1);
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

}
public class Event extends Task {
    private String datetime;

    Event(String name, String datetime, boolean done) {
        super(name, done);
        this.datetime = datetime;
    }

    Event(String name, String datetime) {
        this(name, datetime, false);
    }

    @Override
    public String storageString() {
        return "E," +
                (super.getDone() ? "1," : "0,") +
                super.getName() +
                "," +
                this.datetime;
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + datetime + ")";
    }
}

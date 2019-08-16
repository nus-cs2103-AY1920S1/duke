public class Event extends Task {
    private String datetime;

    Event(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + datetime + ")";
    }
}

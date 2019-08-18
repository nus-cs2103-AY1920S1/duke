import org.w3c.dom.html.HTMLImageElement;

public class Event extends Task{
    protected String timing;
    public Event(String name, String timing) {
        super(name);
        this.timing = timing;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timing + ")";
    }
}

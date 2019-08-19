//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
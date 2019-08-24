// Adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html 

import java.util.Date;

public class Event extends Task {	
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
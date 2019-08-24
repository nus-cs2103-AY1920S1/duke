package duke.task;

public class Event extends Task{
    private char shortForm = 'E';
    protected String timing;

    //Used when user entered command
    public Event(String name, String timing) {
        super(name);
        this.timing = timing;
    }

    //Used when program loads data from text
    public Event(String name, String timing, boolean isDone) {
        super(name, isDone);
        this.timing = timing;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public char getShortForm() {
        return shortForm;
    }

    @Override
    public String toString() {
        return "[" + getShortForm() + "]" + super.toString() + " (at: " + timing + ")";
    }
}

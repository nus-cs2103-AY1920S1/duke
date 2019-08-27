public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.identity = 'E';
        this.at = at;
    }

    public Event(int intDone, String description, String at) {
        super(intDone, description);
        this.identity = 'E';
        this.at = at;
        if(intDone==1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toTextFile() {
        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description + " | " + this.at;
    }

}

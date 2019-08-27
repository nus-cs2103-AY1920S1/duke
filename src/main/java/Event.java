public class Event extends Task {

    protected String dateAndTime;

    public Event(String description, String at) {
        super(description);

        this.dateAndTime = at;

        this.identity = 'E';


    }

    public Event(int intDone, String description, String dateAndTime) {
        super(intDone, description);
        this.identity = 'E';
        this.dateAndTime = dateAndTime;
        if(intDone==1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }

    public String toTextFile() {
        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description + " | " + this.dateAndTime;
    }

}

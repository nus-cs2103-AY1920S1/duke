public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        //System.out.printf("\t[E][%s] %s\n", getStatusIcon(), description);
        System.out.println("\t" + this);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

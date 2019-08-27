public class Deadline extends Task {

    protected String by;


    public Deadline(String description, String by) {
        super(description);
        this.identity = 'D';
        this.by = by;
    }

    public Deadline(int intDone, String description, String by) {
        super(intDone, description);
        this.identity = 'D';
        this.by = by;
        if(intDone==1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toTextFile() {
        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description + " | " + this.by;
    }

}

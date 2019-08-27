public class Deadline extends Task {

    protected String date;


    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.identity = 'D';
    }


    public Deadline(int intDone, String description, String date) {
        super(intDone, description);
        this.identity = 'D';
        this.date = date;
        if(intDone==1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }

    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String toTextFile() {
        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description + " | " + this.date;
    }

}

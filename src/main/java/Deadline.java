public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String desc, String by, boolean isDone){
        super(desc, isDone);
        this.by = by;
    }

    @Override
    public String writeFormat(){
        return "D " + isDone + " " + description + "/" + by;
    }

    @Override
    public String toString(){
        return "[D]" + super.getTask() + " (by: " + by + ")";
    }
}

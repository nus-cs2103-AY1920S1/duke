public class Deadline extends Task{
    protected Date by;

    public Deadline(String description, Date by){
        super(description);
        this.by = by;
    }

    @Override
    public String toFile(){
        return "D | " + (isDone?"1":"0") + " | " + this.description + " | " + this.by + "\n";
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

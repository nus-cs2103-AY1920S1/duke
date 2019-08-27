package slave.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, int id, String by){
        super(description, id);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    @Override
    public String getDate(){
        return this.by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

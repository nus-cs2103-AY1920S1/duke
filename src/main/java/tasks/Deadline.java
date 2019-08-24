package tasks;

import tasks.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String writer() {
        String text = "D | ";
        if(getStatus() == false){
            text = text.concat("0 | "+getDescription()+" | "+getBy());
        }else{
            text = text.concat("1 | "+getDescription()+" | "+getBy());
        }
        return text;
    }
}

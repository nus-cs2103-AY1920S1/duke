package tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, Date by, int doner){
        super(description);
        if(doner == 1){
            super.completed();
        }
        this.by = by;
    }

    @Override
    public String save(){
        int a = 0;
        if(super.isDone){
            a = 1;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        return "D|" + a + "|" + super.description + "|" + formatter.format(by);
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        return "[D]" + super.toString() + "(by: " + formatter.format(by) + ")";
    }
}
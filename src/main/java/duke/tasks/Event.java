package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at){
        super(description);
        this.at = at;
    }

    public Event(String description, Date at, int doner){
        super(description);
        if(doner == 1){
            super.completed();
        }
        this.at = at;
    }

    @Override
    public String save(){
        int a = 0;
        if(super.isDone){
            a = 1;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        return "E|" + a + "|" +  super.description + "|" + formatter.format(at);
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        return "[E]" + super.toString() + "(at: " + formatter.format(at) + ")";
    }
}
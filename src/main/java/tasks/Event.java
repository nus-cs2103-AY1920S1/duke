package tasks;

import tasks.Task;

public class Event extends Task {
    protected String at;
    protected String year;
    protected String month;
    protected String day;
    protected String time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] splits = at.split("[/ ]");
        day = splits[0];
        month = splits[1];
        year = splits[2];
        time = splits[3];
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String writer() {
        String text = "E | ";
        if(getStatus() == false){
            text = text.concat("0 | "+getDescription()+" | "+getAt());
        }else{
            text = text.concat("1 | "+getDescription()+" | "+getAt());
        }
        return text;
    }
}

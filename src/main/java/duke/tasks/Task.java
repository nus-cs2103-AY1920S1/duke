package duke.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    //get the completion status of the task
    public String taskCompletion(){
        String status = getStatusIcon();
        return ("[" + status + "] " + description);
    }

    //Change task to completed;
    public void completed(){
        this.isDone = true;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    //get the tick or cross
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    //save to file
    public String save(){
        return "Saving";
    }

    //print the line for fromatting
    public static Date dateTimeConversion(String dateTime){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        String dateInString = dateTime;
        try {
            Date date = formatter.parse(dateInString);
            return date;
        } catch (ParseException e){
            System.out.println("Not valid date and time");
            Date date = null;
            return date;
        }
    }

    @Override
    public String toString(){
        String status = getStatusIcon();
        return ("[" + status + "] " + description);
    }
}
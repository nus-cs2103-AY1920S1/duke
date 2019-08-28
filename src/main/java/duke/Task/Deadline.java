package duke.task;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDo extends Task{
    private Date givenTime;

    public ToDo(String taskDescription, String isDone, String givenTime){
        super(taskDescription, isDone);

        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy kkmm");
            this.givenTime = df.parse(givenTime, new ParsePosition(0));
        } catch (NullPointerException n){
            throw new NullPointerException();
        }
    }



}

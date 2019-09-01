package duke.task;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Task{
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String parseDate(String input){
        SimpleDateFormat userInputParser = new SimpleDateFormat("d/MM/yyyy HHmm");
        SimpleDateFormat storageInputParser = new SimpleDateFormat("d 'of' MMMM yyyy',' h'.'mma");
        try{
            Date parsedDate;
            try{
                parsedDate = userInputParser.parse(input);
            }catch(ParseException e){
                try{
                    String inputWithRemovedOrdinal = input.replaceAll("(?<=\\d)(st|nd|rd|th)", "");
                    parsedDate = storageInputParser.parse(inputWithRemovedOrdinal);
                }catch(ParseException f){
                    throw f;
                }
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            if(dayOfMonth < 10 || dayOfMonth > 19){
                switch (dayOfMonth % 10) {
                    case 1:
                        return new SimpleDateFormat("d'st' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                    case 2:
                        return new SimpleDateFormat("d'nd' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                    case 3:
                        return new SimpleDateFormat("d'rd' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                    default:
                        return new SimpleDateFormat("d'th' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
                }
            }else{
                return new SimpleDateFormat("d'th' 'of' MMMM yyyy',' h'.'mma").format(parsedDate);
            }
        } catch(ParseException e){
            System.out.println("ERROR PARSING DATE");
            return "";
        }
    }

    public String getStatusIcon(){
        return (isDone ? "✓" : "✗");
    }

    public String getTaskDescription(){
        return this.description;
    }

    public abstract Task markAsDone() throws DukeException;

    public String getTaskType(){
        return this.taskType;
    }

    public abstract String getTaskStatus();

    public abstract String getStoredTaskStatus();
}

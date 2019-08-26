import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime pattern;
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, String time) {
//        LocalDateTime convert = LocalDateTime.parse(time.trim(), dateTimeFormatter);
//        new Task(description.trim(), convert);
        this.description = description;
        pattern = LocalDateTime.parse(time, dateTimeFormatter);
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTime() {
        return  pattern.format(formatter2);
    }

    @Override
    public String toString(){
//       System.out.println("debug: " + getStatusIcon());
        return "["+ getStatusIcon() + "] " + description;
    }
}
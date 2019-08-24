import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDateTime at, to;
    public Event(String content, LocalDateTime at, LocalDateTime to){
        super(content);
        this.at = at;
        this.to = to;
    }
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "[E]"+super.toString()+ " (at: " + at.format(formatter) + " to " + to.format(formatter) + ")";
    }
    public String toFile(){
        return "E,"+super.toFile()+","+at;
    }
}
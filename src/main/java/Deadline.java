import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime by;
    public Deadline(String content, LocalDateTime by){
        super(content);
        this.by = by;
    }
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "[D]"+super.toString()+ " (by: " + by.format(formatter) + ")";
    }
    public String toFile(){
        return "D,"+super.toFile()+","+by;
    }
}
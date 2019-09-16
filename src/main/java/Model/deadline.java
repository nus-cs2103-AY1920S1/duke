package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class deadline extends Task{
    private final char symbol = 'D';
    private String details;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime time;

    public deadline(String description, String details){
        super(description);
        this.details = details;
        try{
            System.out.println(details);
            this.time = LocalDateTime.parse(details.trim(), DATE_TIME_FORMATTER);
        } catch(Exception E){
            this.time = null;
        }
    }

    public deadline(String description, Boolean isDone, String details){
        super(description);
        this.details = details;
        try{
            this.time = LocalDateTime.parse(details.trim(), DATE_TIME_FORMATTER);
        } catch(Exception E){
            this.time = null;
        }
        this.setIsDone(isDone);
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String getDetails(){
        return this.details;
    }

    public String getTime() {
        if(this.time == null){
            return getDetails();
        } else {
            return this.time.toString();
        }
    }
}

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected LocalDateTime byDateTime;

    public Deadline(String description, String byDateTime) throws DukeException {
        super(description);
        this.taskType = TaskType.DEADLINE;
        try {
            this.byDateTime = LocalDateTime.parse(byDateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime Parsing Failed: DateTime Format should follow \"dd/MM/yyyy HH:mm\" " +
                    "format." + e.getMessage());
        }
    }

    public String getByDateTimeString(){
        try{
            String string = byDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return string;
        } catch (DateTimeException e) {
            return "DateCannotBeFormatted";
        }
    }

    @Override
    public String toSaveString() {
        String saveString = super.toSaveString() + "@@@" + this.getByDateTime();
        return saveString;
    }
    
    @Override
    public String toString() {
        String str = "["
                + "D"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription()
                + " (by: "
                + this.getByDateTimeString()
                + ")";
        return str;
    }
}
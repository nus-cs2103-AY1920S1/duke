import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private String time;

    private LocalDateTime dateTime;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
        this.type = TaskType.DEADLINE;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            this.dateTime = LocalDateTime.parse(time, f);
        } catch (DateTimeParseException e) {
        }
    }





    public static Deadline createDeadline(String [] tokens) {
        StringBuilder description = new StringBuilder();
        StringBuilder time = new StringBuilder();
        StringBuilder builder = description;
        boolean isDescription = true;
        for (int i = 1; i < tokens.length - 1 ; i++) {
            String curr = tokens[i];
            if (curr.equals("/by")) {
                isDescription = !isDescription;
                builder = time;
            } else {
                builder.append(tokens[i]);
                builder.append(" ");
            }
        }
        builder.append(tokens[tokens.length-1]);
        return new Deadline(description.toString(),time.toString());
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", getStatusIcon(),
                getDescription(), getTime());
    }
}
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDateTime time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            time = LocalDateTime.parse(at, formatter);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    @Override
    public StringBuilder saveData() {
        return new StringBuilder("E|").append(super.saveData()).append("|").append(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
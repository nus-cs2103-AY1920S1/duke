import java.util.Scanner;

public class Event extends Task {
    public Event(String description, String date) {
        super("[E]", description, date);
    }

    public String toString() {
        return String.format("%s%s %s(at:%s)", this.type,
                this.getStatusIcon(),
                this.description,
                this.date);
    }
}

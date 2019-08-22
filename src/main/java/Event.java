import java.lang.StringBuilder;

public class Event extends Task {

    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Event createEvent(String [] tokens) {
        StringBuilder description = new StringBuilder();
        StringBuilder time = new StringBuilder();
        StringBuilder builder = description;
        boolean isDescription = true;
        for (int i = 1; i < tokens.length - 1 ; i++) {
            String curr = tokens[i];
            if (curr.equals("/at")) {
                isDescription = !isDescription;
                builder = time;
            } else {

                builder.append(tokens[i]);
                builder.append(" ");
            }
        }
        builder.append(tokens[tokens.length-1]);
        return new Event(description.toString(),time.toString());
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        return String.format("[E][%s] %s(at: %s)", getStatusIcon(),
                getDescription(), getTime());
    }
}
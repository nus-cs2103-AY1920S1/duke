import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private String time;

    private Event(String description, String time) {
        super(description);
        this.time = time;
    }

    static Command getCommand(List<Task> tasks) {
        return words -> {
            int separator = List.of(words).indexOf("/at");
            String[] descriptionArr = Arrays.copyOfRange(words, 1, separator);
            String[] timeArr = Arrays.copyOfRange(words, separator + 1, words.length);
            String description = String.join(" ", descriptionArr);
            String time = String.join(" ", timeArr);
            Task task = new Event(description, time);
            tasks.add(task);
            return List.of("Got it. I've added this task:", "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        };
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}

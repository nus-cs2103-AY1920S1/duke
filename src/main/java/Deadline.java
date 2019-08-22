import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private String time;

    private Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    static Command getCommand(List<Task> tasks) {
        return words -> {
            int separator = List.of(words).indexOf("/by");
            String[] descriptionArr = Arrays.copyOfRange(words, 1, separator);
            String[] timeArr = Arrays.copyOfRange(words, separator + 1, words.length);
            String description = String.join(" ", descriptionArr);
            String time = String.join(" ", timeArr);
            Task task = new Deadline(description, time);
            tasks.add(task);
            return List.of("Got it. I've added this task:", "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        };
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}

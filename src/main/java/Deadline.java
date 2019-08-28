import java.util.List;

public class Deadline extends Task {
    private String time;

    private Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    static Command getCommand(List<Task> tasks) {
        return words -> {
            List<String> wordList = List.of(words);
            int separator = wordList.indexOf("/by");
            if (separator == -1) {
                throw new DukeException("A deadline must have a time.");
            } else if (separator == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (separator == words.length - 1) {
                throw new DukeException("The time of a deadline cannot be empty.");
            }
            String description = String.join(" ", wordList.subList(1, separator));
            String time = String.join(" ", wordList.subList(separator + 1, words.length));
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

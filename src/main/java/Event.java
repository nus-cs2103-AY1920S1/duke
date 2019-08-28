import java.util.List;

public class Event extends Task {
    private String time;

    private Event(String description, String time) {
        super(description);
        this.time = time;
    }

    static Command getCommand(List<Task> tasks) {
        return words -> {
            List<String> wordList = List.of(words);
            int separator = wordList.indexOf("/at");
            String description = String.join(" ", wordList.subList(1, separator));
            String time = String.join(" ", wordList.subList(separator + 1, words.length));
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

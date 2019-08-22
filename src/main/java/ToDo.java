import java.util.Arrays;
import java.util.List;

public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    static Command getCommand(List<Task> tasks) {
        return words -> {
            String[] nameArr = Arrays.copyOfRange(words, 1, words.length);
            Task task = new ToDo(String.join(" ", nameArr));
            tasks.add(task);
            return List.of("Got it. I've added this task:", "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        };
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    static Command getCommand(List<Task> tasks, Storage storage) {
        return words -> {
            if (words.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String[] nameArr = Arrays.copyOfRange(words, 1, words.length);
            Task task = new ToDo(String.join(" ", nameArr));
            tasks.add(task);
            storage.store(tasks);
            return List.of("Got it. I've added this task:", "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        };
    }

    @Override
    List<String> getSaveList() {
        List<String> list = new ArrayList<>();
        list.add("T");
        list.addAll(super.getSaveList());
        return list;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

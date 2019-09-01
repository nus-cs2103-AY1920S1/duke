import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    /**
     * Returns a Command which generates objects of this task given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding this task.
     * @return A Command which generates tasks.
     */
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

    /**
     * Returns a list of strings representing this task so that it can be saved.
     *
     * @return A representation of this task as a list for saving.
     */
    @Override
    List<String> getSaveList() {
        List<String> list = new ArrayList<>();
        list.add("T");
        list.addAll(super.getSaveList());
        return list;
    }

    /**
     * Returns this task as a string to display to the user.
     *
     * @return This task as a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

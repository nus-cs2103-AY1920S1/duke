import java.lang.StringBuilder;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int id) {
        return this.tasks.get(id - 1);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int id) {
        return this.tasks.remove(id - 1);
    }

    public int numberOfTasks() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Retrieve the length of the task list and the number of digits of the maximum task ID
        int length = this.numberOfTasks();

        if (length == 0) {
            return "Your task list is empty!";
        } else {
            // Build format string to print all task IDs with the same width, left-padded with spaces
            long numDigits = Math.round(Math.log10(length) + 0.5);
            String formatTemplate = "%1$" + numDigits + "s";

            for (int i = 0; i < length; i++) {
                result.append(String.format(formatTemplate, i + 1))
                      .append(String.format(". %s\n", this.tasks.get(i).toString()));
            }

            // Trim trailing newline
            result.setLength(result.length() - 1);
            return result.toString();
        }
    }
}

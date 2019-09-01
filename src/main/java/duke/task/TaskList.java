package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<String> lines) {
        for (String line : lines) {
            tasks.add(parseTask(line));
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<String> getAsLines() {
        List<String> save = new ArrayList<>();
        for (Task task : tasks) {
            save.add(getSaveString(task));
        }
        return save;
    }

    /**
     * Converts the given task into a string for saving.
     *
     * @param task Task to convert into a string.
     * @return String for saving.
     */
    private String getSaveString(Task task) {
        StringJoiner joiner = new StringJoiner("|");
        for (String field : task.getSaveList()) {
            joiner.add(escape(field));
        }
        return joiner.toString();
    }

    /**
     * Parses the given string as a task.
     *
     * @param line Line to parse.
     * @return Parsed task.
     */
    private Task parseTask(String line) {
        String[] data = line.split("\\|");
        Task task;
        switch (data[0]) {
            case "T":
                task = new ToDo(unescape(data[2]));
                break;
            case "D":
                task = new Deadline(unescape(data[2]), unescape(data[3]));
                break;
            case "E":
                task = new Event(unescape(data[2]), unescape(data[3]));
                break;
            default:
                return null;
        }
        if (data[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Escapes a string to use for saving a task.
     *
     * @param str String to escape.
     * @return Escaped string.
     */
    private String escape(String str) {
        return str.replace("\\", "\\\\").replace("|", "\\p");
    }

    /**
     * Unescapes an escaped string to use for reconstructing a task.
     *
     * @param str String to unescape.
     * @return Original string.
     */
    private String unescape(String str) {
        // Split the array at double backslashes, keeping trailing empty strings
        String[] parts = str.split("\\\\\\\\", -1);

        // Join with single backslash after unescaping
        StringJoiner joiner = new StringJoiner("\\");
        for (String part : parts) {
            joiner.add(part.replace("\\p", "|"));
        }
        return joiner.toString();
    }
}

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

    private String getSaveString(Task task) {
        StringJoiner joiner = new StringJoiner("|");
        for (String field : task.getSaveList()) {
            joiner.add(escape(field));
        }
        return joiner.toString();
    }

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

    private String escape(String str) {
        return str.replace("\\", "\\\\").replace("|", "\\p");
    }

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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

class Storage {
    private Path path;

    Storage(String location) {
        path = Paths.get(location);
    }

    /**
     * Saves the given list of tasks.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If the tasks cannot be saved.
     */
    void store(List<Task> tasks) throws IOException {
        List<String> save = new ArrayList<>();
        for (Task task : tasks) {
            save.add(getSaveString(task));
        }
        Files.write(path, save);
    }

    /**
     * Loads and returns the list of tasks.
     *
     * @return List of tasks loaded.
     * @throws IOException If the tasks cannot be loaded.
     */
    List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            tasks.add(parseTask(line));
        }
        return tasks;
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

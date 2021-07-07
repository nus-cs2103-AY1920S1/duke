package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Storage object in the Duke. A <code>Storage</code> object corresponds to
 * writing and loading data to a file.
 */
class Storage {
    private String filepath;
    private ArrayList<Task> alist;
    private final char tick = '✓';

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns the list of tasks saved previously.
     * If there is input output exception, Duke Exception is thrown.
     *
     * @return ArrayList list of tasks.
     * @throws DukeException If there is input output exception.
     */
    ArrayList<Task> load() throws DukeException {
        alist = new ArrayList<>();
        String line;
        BufferedReader br;
        String description;
        String tags;
        try {
            br = new BufferedReader(new FileReader(filepath));
            while ((line = br.readLine()) != null) {
                description = line.substring(7);
                tags = "";
                if (description.contains("#")) {
                    String[] array = description.split("#", 2);
                    description = array[0];
                    tags = array[1];
                }
                char eventType = line.charAt(1);
                char symbol = line.charAt(4);
                if (eventType == 'T') {
                    loadTodo(tags,symbol, description);
                } else if (eventType == 'D') {
                    loadDeadline(tags, symbol, description);
                } else {
                    assert eventType == 'E' : "Event loaded from hard disk should be event type";
                    loadEvent(tags, symbol, description);
                }
            }
            br.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
        return alist;
    }

    private void loadTodo(String tags, char symbol, String description) {
        Todo todo = new Todo(description);
        if (!tags.isEmpty()) {
            loadTags(tags, todo);
        }
        if (symbol == tick) {
            todo.markDone();
        }
        alist.add(todo);
    }

    private void loadDeadline(String tags, char symbol, String description) {
        Deadline deadline = new Deadline(description);
        if (!tags.isEmpty()) {
            loadTags(tags, deadline);
        }
        if (symbol == tick) {
            deadline.markDone();
        }
        alist.add(deadline);
    }

    private void loadEvent(String tags, char symbol, String description) {
        Event event = new Event(description);
        if (!tags.isEmpty()) {
            loadTags(tags, event);
        }
        if (symbol == tick) {
            event.markDone();
        }
        alist.add(event);
    }

    private void loadTags(String tags, Task task) {
        String[] tagArray = tags.split("#");
        for (String t: tagArray) {
            task.addTag(t);
        }
    }

    /**
     * Saves all tasks to the file.
     * If there is input output exception, Duke Exception is thrown.
     *
     * @param tasks  TaskList containing list of tasks
     * @throws DukeException  If there is input output exception.
     */
    void writeToHardDisk(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            for (Task t: tasks.taskList) {
                fileWriter.write(t + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }
}

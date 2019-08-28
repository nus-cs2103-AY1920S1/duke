/**
 * The Storage class deals with storing tasks from the task list
 * and loading tasks from the hard drive.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    /**
     * Constructor for class Storage.
     *
     * @param filePath File path for accessing the hard drive.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks to load into the task list.
     *
     * @return tasks List of tasks.
     * @throws FileNotFoundException If file to load tasks from cannot be found.
     * @throws DukeException If file is empty.
     */
    public List<Task> loadTasks() throws FileNotFoundException, DukeException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        if (f.length() == 0) {
            throw new DukeException();
        }
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] task = input.split(" \\| ");
            switch (task[0]) {
            case "T":
                tasks.add(new ToDo(task[2]));
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                tasks.add(new Event(task[2], task[3]));
                break;
            }
            if (task[1].equals("1")) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks from the task list to the hard drive.
     *
     * @param tasks Task list to save.
     * @throws IOException Throws if an unpredicted error occurs.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getList()) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                fw.write("D | " + d.isDone() + " | " + d.getDescription() + " | " + d.getBy() + "\n");
            } else if (task instanceof Event) {
                Event e = (Event) task;
                fw.write("E | " + e.isDone() + " | " + e.getDescription() + " | " + e.getAt() + "\n");
            } else if (task instanceof ToDo) {
                ToDo t = (ToDo) task;
                fw.write("T | " + t.isDone() + " | " + t.getDescription() + "\n");
            }
        }
        fw.close();
    }
}

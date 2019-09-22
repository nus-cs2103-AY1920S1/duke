package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class deals with storing tasks from the task list
 * and loading tasks from the hard drive.
 */
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
     * Returns a list of tasks to load onto the task list.
     *
     * @return tasks List of tasks.
     * @throws FileNotFoundException If file to load tasks from cannot be found.
     * @throws DukeException If file is empty or corrupted.
     * @throws ParseException If date/time of file is corrupted.
     */
    public List<Task> loadTasks() throws FileNotFoundException, DukeException, ParseException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a");

        if (f.length() == 0) {
            throw new DukeException();
        }

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] task = input.split(" \\| ");
            boolean isPriority;

            if (task[2].equals("H")) {
                isPriority = true;
            } else if (task[2].equals("L")) {
                isPriority = false;
            } else {
                throw new DukeException();
            }

            switch (task[0]) {
            case "T":
                assert task.length == 4 : "ToDo should have 4 components";
                tasks.add(new ToDo(task[3], isPriority));
                break;
            case "D":
                assert task.length == 5 : "Deadline should have 5 components";
                tasks.add(new Deadline(task[3], dateTimeFormat.parse(task[4]), isPriority));
                break;
            case "E":
                assert task.length == 5 : "Event should have 5 components";
                tasks.add(new Event(task[3], dateTimeFormat.parse(task[4]), isPriority));
                break;
            default:
                throw new DukeException();
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
                fw.write("D | " + d.isDone() + " | " + d.getPriority() + " | "
                        + d.getDescription() + " | " + d.getBy() + "\n");
            } else if (task instanceof Event) {
                Event e = (Event) task;
                fw.write("E | " + e.isDone() + " | " + e.getPriority() + " | "
                        + e.getDescription() + " | " + e.getAt() + "\n");
            } else if (task instanceof ToDo) {
                ToDo t = (ToDo) task;
                fw.write("T | " + t.isDone() + " | " + t.getPriority() + " | " + t.getDescription() + "\n");
            }
        }
        fw.close();
    }
}

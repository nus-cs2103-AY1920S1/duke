package duke.storage;

import duke.exception.IllegalDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class dealing with storage.
 */
public class Storage {
    private static final String doneString = "1";
    private String filePath;

    /**
     * Class constructor specifying the path of file that stores data.
     * @param filePath the path of file that stores data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks that are stored in disk.
     * @return a list of tasks that are stored in disk.
     * @throws FileNotFoundException If storage file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath);
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String[] str = in.nextLine().split(" \\| ");
                Task task;
                try {
                    switch (str[0]) {
                    case "T":
                        task = new ToDo(str[2]);
                        break;
                    case "E":
                        task = new Event(str[2], LocalDateTime.parse(str[3]));
                        break;
                    case "D":
                        task = new Deadline(str[2], LocalDateTime.parse(str[3]));
                        break;
                    default:
                        continue;
                    }
                    if (str[1].equals(doneString)) {
                        task.setDone();
                    }
                    list.add(task);
                } catch (IllegalDescriptionException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            if (!f.createNewFile()) {
                throw e;
            }
        } finally {
            return list;
        }
    }

    /**
     * Stores tasks in a task list into the file.
     * @param tasks a list of tasks.
     * @throws IOException If an input or output exception occurred.
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            fw.write(tasks.getTaskAtIndex(i + 1).toStringForFile() + "\n");
        }
        fw.close();
    }
}

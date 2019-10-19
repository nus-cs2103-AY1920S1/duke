package duke;

import duke.exception.DukeException;
import duke.model.TaskList;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.Task;
import duke.model.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class which deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for storage.
     *
     * @param filePath The string representing the path of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads saved data before the program runs, if there is any saved data.
     *
     * @return A list of tasks which is saved locally.
     * @throws DukeException If an error occurs when loading.
     */
    public ArrayList<Task> load() throws DukeException {
        // check for directory existence
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // check for text file existence
        String filePath = this.filePath;
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }

        ArrayList<Task> list = new ArrayList<>();

        // read from file
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] items = line.split("[|]");
                for (int i = 0; i < items.length; i++) {
                    items[i] = items[i].trim();
                }

                if (line.charAt(0) == 'T') {
                    Task todo = new ToDo(items[2], items[1].equals("1"));
                    list.add(todo);
                } else if (line.charAt(0) == 'E') {
                    Task event = new Event(items[2], items[1].equals("1"), items[3]);
                    list.add(event);
                } else if (line.charAt(0) == 'D') {
                    Task deadline = new Deadline(items[2], items[1].equals("1"), items[3]);
                    list.add(deadline);
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return list;
    }

    /**
     * Save the tasks to the file in the directory provided.
     *
     * @param tasks The tasks to be saved after running duke.
     */
    public void save(TaskList tasks) {
        try {
            ArrayList<Task> list = tasks.list;
            String filePath = this.filePath;
            FileWriter fw = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();
            for (Task task : list) {
                sb.append(task.getFileStringFormat()).append("\r\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }
}

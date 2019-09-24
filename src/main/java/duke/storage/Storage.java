package duke.storage;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with the loading and saving of tasks to the assigned file.
 */
public class Storage {

    String filePath;
    // keep track of number of tasks
    private static int count = 0;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new list of tasks and load tasks one by one from the saved file in filepath to this list.
     *
     * @return list of saved tasks.
     * @throws FileNotFoundException if no such file is found in filepath.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        // create a file object
        File f = new File(filePath);
        Scanner load = new Scanner(f);
        while (load.hasNext()) {
            String item = load.nextLine();
            loadTask(item, tasks);
        }
        return tasks;
    }

    /**
     * Figures out the type of task being loaded from saved file, creates the new task and adds to the
     * new list of tasks.
     * For each task being created, check if it is done already and mark it accordingly while loading.
     *
     * @param item entire task as a string.
     * @param tasks new task list to be loaded with tasks.
     */
    private static void loadTask(String item, ArrayList<Task> tasks) {
        if (item.startsWith("T")) {
            // split command into 3 parts
            String[] splitStr = item.split(" \\| ", 3);
            tasks.add(new Todo(splitStr[2]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count++;
        } else if (item.startsWith("D")) {
            // split command into 4 parts
            String[] splitStr = item.split(" \\| ", 4);
            tasks.add(new Deadline(splitStr[2], splitStr[3]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count++;
        } else if (item.startsWith("E")) {
            // split command into 4 parts
            String[] splitStr = item.split(" \\| ", 4);
            tasks.add(new Event(splitStr[2], splitStr[3]));
            if (splitStr[1].equals("1")) {
                tasks.get(count).setDone();
            }
            count++;
        }
    }

    /**
     * Saves the existing list of tasks to the filepath.
     *
     * @param tasklist existing list of tasks.
     * @throws IOException if unable to save file.
     */
    public void saveFile(TaskList tasklist) throws IOException {
        FileWriter save = new FileWriter(filePath);
        for (int i = 0; i < tasklist.size(); i++) {
            save.write(tasklist.get(i).saveTask());
        }
        save.close();
    }

}

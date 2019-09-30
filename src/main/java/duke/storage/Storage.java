package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.DoAfterTasks;

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

    private String filePath;

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
        String[] splitStr = item.split(" \\| ");
        switch (splitStr[0]) {
        case "T":
            tasks.add(new Todo(splitStr[2]));
            checkDone(splitStr[1], tasks.get(tasks.size() - 1));
            break;
        case "D":
            tasks.add(new Deadline(splitStr[2], splitStr[3]));
            checkDone(splitStr[1], tasks.get(tasks.size() - 1));
            break;
        case "E":
            tasks.add(new Event(splitStr[2], splitStr[3], splitStr[4]));
            checkDone(splitStr[1], tasks.get(tasks.size() - 1));
            break;
        case "DA":
            tasks.add(new DoAfterTasks(splitStr[2], splitStr[3]));
            checkDone(splitStr[1], tasks.get(tasks.size() - 1));
            break;
        default:
            assert false : splitStr[0];
            break;
        }
    }

    /**
     * Mark any task loaded as done accordingly.
     *
     * @param splitStr saved as 1 to indicate done and 0 to indicate not done.
     * @param task task to be marked.
     */
    private static void checkDone(String splitStr, Task task) {
        if (splitStr.equals("1")) {
            task.setDone();
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

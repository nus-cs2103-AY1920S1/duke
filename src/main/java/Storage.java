import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a Storage object with the file path assigned.
     *
     * @param filePath Location of task list to load from and store to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the previous session form the hard disk, if any.
     *
     * @return ArrayList of task that has been loaded from previous session.
     * @throws FileNotFoundException If there was no previous session.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String currTask = sc.nextLine();
            if (currTask.charAt(0) == 'T') {
                String[] currArray = currTask.split(" \\| ");
                Todo currTodo = new Todo(currArray[2]);
                if (currArray[1].equals("1")) {
                    currTodo.markAsDone();
                }
                loadedTaskList.add(currTodo);
            } else if (currTask.charAt(0) == 'D') {
                String[] currArray = currTask.split(" \\| ");
                Deadline currDeadline = new Deadline(currArray[2], currArray[3]);
                if (currArray[1].equals("1")) {
                    currDeadline.markAsDone();
                }
                loadedTaskList.add(currDeadline);
            } else {
                String[] currArray = currTask.split(" \\| ");
                Event currEvent = new Event(currArray[2], currArray[3]);
                if (currArray[1].equals("1")) {
                    currEvent.markAsDone();
                }
                loadedTaskList.add(currEvent);
            }
        }
        sc.close();
        return loadedTaskList;
    }

    /**
     * Stores the task list in the hard disk.
     *
     * @param tasks Task list to retrieve.
     * @throws IOException If hard disk cannot be found in the given file path.
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(getSavedListString(tasks));
        fw.close();
    }

    /**
     * Formats the task list to be stored.
     * @param tasks Task list to retrieve.
     * @return All tasks in the list in a simplified format.
     */
    public static String getSavedListString(TaskList tasks) {
        String allTasks = "";
        for (int i = 0; i < tasks.getTasks().size() ; i++) {
            allTasks += tasks.getTasks().get(i).toSave() + "\n";
        }
        return allTasks;
    }
}
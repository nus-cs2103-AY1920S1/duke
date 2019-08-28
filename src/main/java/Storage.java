import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File taskFile;

    /***
     * Class constructor.
     * @param file File instance to be used for storage
     */
    public Storage(File file) {
        this.taskFile = file;
    }

    /***
     * Write input TaskList into local storage
     * @param list TaskList to be written to local storage
     */
    public void writeTaskListToFile(TaskList list) {
        try {
            String textToAdd = "";
            FileWriter fw = new FileWriter(taskFile.getPath());
            for (Task t : list.getTasks()) {
                textToAdd += t.serialize();
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Append a Task into storage file.
     * @param taskToAppend Task to be appended
     */
    public void appendTaskToFile(Task taskToAppend) {
        try {
            FileWriter fw = new FileWriter(taskFile.getPath(), true); // create a FileWriter in append mode
            fw.write(taskToAppend.serialize());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Retrieve and return lists of Tasks from storage file.
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public List<Task> getTaskList() throws FileNotFoundException, DukeException {
        List<Task> taskList = new ArrayList<Task>();
        Scanner s = new Scanner(taskFile); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            Task t = Task.deserialize(s.nextLine());
            taskList.add(t);
        }
        return taskList;
    }
}

import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks in specified path
     *
     * @param tasks
     */
    public void save(String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }

    /**
     * Loads task
     */

//    public List<Task> load() {
//
//    }
}
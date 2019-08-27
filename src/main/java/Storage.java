import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */

class Storage {

    /**
     * Saves tasks in specified path
     *
     * @param filePath
     * @param tasks
     */
    public void save(String filePath, String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }
}
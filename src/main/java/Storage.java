import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Tries to load data from file at filePath.
     *
     * @throws LoadFileFailDukeException On unable to read from file or unable to parse data
     */
    public ArrayList<Task> load() throws LoadFileFailDukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            for (String record : new BufferedReader(new FileReader(filePath)).readLine().split("\\x1e")) {
                tasks.add(Task.parseFileTask(record));
            }
            return tasks;
        } catch (Exception e) {
            throw new LoadFileFailDukeException(filePath);
        }
    }

    /**
     * Tries to write into file at filePath.
     *
     * @param content String to be written into file
     * @throws WriteFileFailDukeException On unable to write to file
     */
    public void rewrite(String content) throws WriteFileFailDukeException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(content);
            fw.close();
        } catch (Exception e) {
            throw new WriteFileFailDukeException();
        }
    }
}

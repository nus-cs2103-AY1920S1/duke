import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    /**
     * Initialise filePath for continuous referencing
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Try to load raw data from filePath and parse into ArrayList of Tasks
     *
     * @return ArrayList of Tasks
     * @throws LoadFileFailDukeException if file or raw data can't be loaded
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
     * Try to rewrite entire file defined by filePath.
     *
     * @param content data to be written over into file at filePath
     * @throws WriteFileFailDukeException if directory does not exists or something unexpected happens
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

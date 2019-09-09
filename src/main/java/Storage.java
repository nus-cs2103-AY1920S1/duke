import java.util.ArrayList;

/**
 * Handles saving and loading of file locally.
 */

public class Storage {

    private String filePath;

    /**
     * Construct a object that streamlines loading and saving of file.
     *
     * @param filePath Directory of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Loads tasks.txt file from the directory given.
     *
     * @return List of tasks.
     * @throws DukeException If file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        assert this.getFilePath() != "" : "File path should not be empty!";

        LoadFile lf = new LoadFile(this.getFilePath());
        ArrayList<Task> taskArr = lf.loadTaskFromFile();
        return taskArr;
    }

    /**
     * Saves the tasks into a text file format in the file path allocated.
     *
     * @param tasks List of tasks.
     */
    public void save(TaskList tasks) {
        assert this.getFilePath() != "" : "File path should not be empty!";

        WriteFile wf = new WriteFile(this.getFilePath());
        wf.writeTaskToFile(tasks.getTaskArr());
    }
}

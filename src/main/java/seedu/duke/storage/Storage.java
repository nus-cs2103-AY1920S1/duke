package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class Storage {
    public String path;

    public Storage(String filePath) {
        this.path = filePath;
    }

    public File load() {
        return new File(path);
    }

    /**
     * Saves the taskslist to hard disk.
     * @param tasks tasks of the task list
     * @throws IOException error
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : tasks) {
            String replyToFile = task.writeToFile();
            fw.write(replyToFile);
        }
        fw.close();
    }
}

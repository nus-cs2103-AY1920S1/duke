package seedu.duke.storage;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public String path;

    public Storage(String filePath) {
        this.path = filePath;
    }

    public File load() {
        return new File(path);
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : tasks) {
            String replyToFile = task.writeToFile();
            fw.write(replyToFile);
        }
        fw.close();
    }
}

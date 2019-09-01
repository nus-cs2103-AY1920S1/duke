package duke;

import duke.task.TaskList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private Path path;

    public Storage(String filepath) {
        this.path = Paths.get(filepath).toAbsolutePath().normalize();
    }

    public TaskList load() throws IOException, ClassNotFoundException {
        if (!Files.exists(path)) {
            return new TaskList();
        }
        FileInputStream fis = new FileInputStream(path.toString());
        ObjectInputStream ois = new ObjectInputStream(fis);
        TaskList taskList = (TaskList) ois.readObject();
        ois.close();
        return taskList;
    }

    public void write(TaskList taskList) throws IOException {
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }
        FileOutputStream fos = new FileOutputStream(path.toString());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList);
        oos.close();
    }
}

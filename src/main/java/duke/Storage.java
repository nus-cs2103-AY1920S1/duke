package duke;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Storage {
    File directoryPath;
    File saveFile;

    public Storage(String filepath) {
        this.directoryPath = new File(filepath);
        directoryPath.mkdirs();
        this.saveFile = new File(filepath + File.separator + "list.bin");
    }

    public TaskList load() throws DukeException {
        try {
            FileInputStream load = new FileInputStream(this.saveFile);
            ObjectInputStream loadList = new ObjectInputStream(load);
            TaskList taskList = (TaskList) loadList.readObject();
            loadList.close();
            return taskList;
        } catch (Exception e) {
            throw new DukeException("Existing save not found! Creating new task list.");
        }
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            FileOutputStream save = new FileOutputStream(this.saveFile);
            ObjectOutputStream saveList = new ObjectOutputStream(save);
            saveList.writeObject(taskList);
            saveList.close();
        } catch (Exception e) {
            throw new DukeException("Failed to save!");
        }
    }
}

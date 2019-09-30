package duke.util;

import duke.task.TaskList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private String filePath;
    private Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    private boolean hasDirectory() {
        // check if parent directory exists. if not, create it
        File file = new File(this.filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            return false;
        } else {
            return true;
        }
    }
    private boolean hasFile() {
        File file = new File(this.filePath);

        // check if file exists
        if (!file.exists()) {
            ui.showMessage(UiMessage.TASKS_NOT_FOUND);
            return false;
        }

        return true;
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        // if directory does not exist, or file does not exist, no need to load from file
        if (!hasDirectory() || !hasFile()) {
            return tasks;
        }

        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            tasks = (TaskList) ois.readObject();

            ois.close();
            fis.close();

            String message = UiMessage.TASKS_IMPORTED.getMessage() + " " + this.filePath;
            ui.showMessage(message);
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        // first, check if task list is empty. if so, do not save
        if (tasks.isEmpty()) {
            ui.showMessage(UiMessage.TASKS_NOT_SAVED);
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(tasks);

            oos.close();
            fos.close();

            String message = UiMessage.TASKS_SAVED.getMessage() + " " + this.filePath;
            ui.showMessage(message);
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}

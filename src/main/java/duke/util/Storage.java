package duke.util;

import duke.task.TaskList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private String filePath;
    private String tasksImportedMessage = "Success! Your tasks have been imported from: ";
    private String tasksSavedMessage = "Success! Your tasks have been saved to: "; // should be in Ui class?
    private String tasksNotSavedMessage = "Your task list is empty! Adios :)";
    private String fileNotFoundMessage = "Existing tasks file not found! Starting duke afresh...";

    public Storage(String filePath) {
        this.filePath = filePath;
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
            System.out.println(fileNotFoundMessage);
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

        // for each line in file
            // create corresponding task
            // add task to list

        try {
            FileInputStream fis = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            tasks = (TaskList) ois.readObject();

            ois.close();
            fis.close();

            System.out.println(tasksImportedMessage);
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        // first, check if task list is empty. if so, do not save
        if (tasks.isEmpty()) {
            System.out.println(tasksNotSavedMessage);
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(tasks);

            oos.close();
            fos.close();

            System.out.println(tasksSavedMessage + this.filePath);
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}

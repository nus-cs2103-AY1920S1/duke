package duke.command;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Save {

    static boolean save(Storage storage, ArrayList<Task> taskList) {
        try {
            storage.saveToFile(taskList);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing task to disk. Your changes were not saved. Check your file permissions?");
            return false;
        }
    }
}

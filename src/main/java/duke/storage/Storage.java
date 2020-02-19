package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.logic.TaskList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.lang.StringBuilder;

/**
 * Storage class to load and save the TaskList into the program.
 */
public class Storage {

    public TaskList tasks = null;

    /**
     * Saves the TaskList object into a .tmp file.
     *
     * @param tasks TaskList to be saved.
     * @throws Exception in case of file not found exception.
     */
    //Solution below adapted from https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
    public void save(TaskList tasks) throws DukeException {
        try {
            int size = tasks.getSize();
            //may have to catch error if no items in list
            StringBuilder listOfTask = new StringBuilder();
            for (int i = 0; i < size; i++) {
                listOfTask.append(i + 1 + ". " + tasks.getTask(i) + "\n" + "     ");
            }
            PrintWriter writer = new PrintWriter(new FileOutputStream("list.txt", false));
            writer.print("     " + listOfTask);
            writer.close();

            FileOutputStream fos = new FileOutputStream("t.tmp", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads TaskList from a .tmp file.
     *
     * @param tasks performs a deep copy of the loaded TaskList into this tasks parameter.
     * @throws Exception in case of file not found exception.
     */
    //Solution below adapted from https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
    public void load(TaskList tasks) throws DukeException {
        try {
            FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList temp = new TaskList();
            temp = (TaskList) ois.readObject();
            for (Task task : temp.getTasks()) {
                tasks.addTask(task);
            }
            ois.close();
        } catch (IOException e) {
            File f = new File("/t.tmp");
            f.getParentFile().mkdirs();
        } catch (ClassNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }
}

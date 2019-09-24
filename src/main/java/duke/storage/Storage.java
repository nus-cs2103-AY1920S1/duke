package duke.storage;

import duke.task.Task;
import duke.exception.DukeIoException;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String pathToFile;
    private List<Task> taskList = new ArrayList<>();

    private static final String MSG_IOE_EXCEPTION = "Sorry, something went wrong reading the file. > <";

    /**
     * Constructs a Storage object.
     *
     * @param pathToFile Path to the file accessed by the Storage object.
     */
    public Storage(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    /**
     * Loads the task list as a List of Task from the local file.
     *
     * @return List of Task objects.
     * @throws DukeIoException If errors occurs when accessing the file that contains the list.
     */
    public List<Task> load() throws DukeIoException {
        try {
            //Solution below adapted from https://github.com/podocarp/duke.git
            File f = new File(pathToFile);
            f.createNewFile();

            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            taskList = (ArrayList<Task>) objectInputStream.readObject();

            objectInputStream.close();
            return taskList;
        } catch (EOFException e) {
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeIoException(MSG_IOE_EXCEPTION);
        }
    }

    /**
     * Outputs a List of Task as a local copy of txt file.
     *
     * @param lst Task list to be saved.
     * @throws DukeIoException If errors occurs when accessing the file that contains the list.
     */
    public void save(List<Task> lst) throws DukeIoException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathToFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lst);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new DukeIoException(MSG_IOE_EXCEPTION);
        }
    }
}

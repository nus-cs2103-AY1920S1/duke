package duke;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.tasks.TaskList;

public class Storage {
    private static final String FILEPATH = "data/duke.txt";

    /**
     * Retrieve data from the storage file.
     * @return the task list stored in data file
     * @throws DukeException if errors occur when loading data
     */
    public TaskList loadData() throws DukeException {
        try {
            FileInputStream file = new FileInputStream(FILEPATH);
            if (file.available() == 0) {
                return new TaskList();
            }
            ObjectInputStream object = new ObjectInputStream(file);

            TaskList tasks = (TaskList) object.readObject();
            object.close();

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("The data file is not found.");
        } catch (ClassNotFoundException e) {
            throw new DukeException("The data file is corrupted.");
        } catch (IOException e) {
            throw new DukeException("The data file is empty.");
        }
    }

    /**
     * Store data into the storage file.
     * @param tasks the task list of the project
     * @throws DukeException if errors occur when saving data
     */
    public void saveData(TaskList tasks) throws DukeException {
        try {
            FileOutputStream file = new FileOutputStream(FILEPATH);
            ObjectOutputStream object = new ObjectOutputStream(file);

            object.writeObject(tasks);
            object.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("The data file is not found.");
        } catch (IOException e) {
            //throw new DukeException("Cannot save data to the file.");
            throw new DukeException(e.getMessage());
        }
    }
}

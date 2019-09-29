package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.exception.DukeException;
import duke.model.Model;

public class Storage {
    private static final String FILEPATH = System.getProperty("user.dir") + "/dukeData/duke.txt";

    /**
     * Retrieve data from the storage file.
     * @return the task list stored in data file
     * @throws DukeException if errors occur when loading data
     */
    public Model loadData() throws DukeException {
        try {
            FileInputStream file = new FileInputStream(FILEPATH);
            if (file.available() == 0) {
                return new Model();
            }
            ObjectInputStream object = new ObjectInputStream(file);

            Model model = (Model) object.readObject();
            object.close();

            return model;
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
     * @param model the model of Duke application
     * @throws DukeException if errors occur when saving data
     */
    public void saveData(Model model) throws DukeException {
        try {
            // Create the file if it does not exist.
            File dataFile = new File(FILEPATH);
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
            FileOutputStream file = new FileOutputStream(dataFile, false);
            ObjectOutputStream object = new ObjectOutputStream(file);

            object.writeObject(model);
            object.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("The data file is not found.");
        } catch (IOException e) {
            throw new DukeException("Cannot save data to the file.");
        }
    }
}

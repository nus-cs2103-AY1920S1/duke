package duke;

import duke.exception.LoadFileFailDukeException;
import duke.exception.WriteFileFailDukeException;
import place.Place;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private String filePath;

    /**
     * Initialise filePath for continuous referencing.
     *
     * @param filePath filePath to save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Try to load raw data from filePath and deserialize everything.
     *
     * @throws LoadFileFailDukeException if file or raw data can't be loaded
     */
    @SuppressWarnings("unchecked")
    public TaskList load() throws LoadFileFailDukeException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            Place.setRefLatLong((HashMap) ois.readObject());
            Place.setRefAlias((HashMap) ois.readObject());
            return (TaskList) ois.readObject();
        } catch (Exception e) {
            throw new LoadFileFailDukeException(filePath);
        }
    }

    /**
     * Try to rewrite entire file defined by filePath.
     *
     * @param tasks TaskList to be written over into file at filePath
     * @throws WriteFileFailDukeException if directory does not exists or unable to write to file
     */
    public void rewrite(TaskList tasks) throws WriteFileFailDukeException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(Place.getRefLatLong());
            oos.writeObject(Place.getRefAlias());
            oos.writeObject(tasks);
        } catch (Exception e) {
            throw new WriteFileFailDukeException();
        }
    }
}

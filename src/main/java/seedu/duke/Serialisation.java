package seedu.duke;

import seedu.duke.tasks.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialisation {
    public final static String DATA_FILE_PATH = "duke.dat";

    private Serialisation() {
    }

    public static TaskList deserialise() {
        if (!new File(DATA_FILE_PATH).exists()) {
            return null;
        }

        try (FileInputStream file = new FileInputStream(DATA_FILE_PATH);
             ObjectInputStream ois = new ObjectInputStream(file)) {
            return (TaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while loading data file.");
            e.printStackTrace();
        }

        return null;
    }

    public static void serialise(TaskList tasks) {
        try (FileOutputStream file = new FileOutputStream(DATA_FILE_PATH);
             ObjectOutputStream ois = new ObjectOutputStream(file)) {
            ois.writeObject(tasks);
        } catch (IOException e) {
            System.err.println("Error while writing data file.");
            e.printStackTrace();
        }
    }
}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/** This class handles the saving and loading of tasks. */
public class Storage {
    private static String path;

    /**
     * Sets the path to load and save from.
     *
     * @param path The path to load and save from.
     */
    public static void setPath(String path) {
        Storage.path = path;
    }

    /**
     * Save tasks.
     *
     * @param storage The list of tasks to store.
     */
    public static void saveTasks(ArrayList<Task> storage) {
        try {
            FileOutputStream fs = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(storage);
            oos.close();
        } catch (Exception e) {
            // Either FileNotFoundException or IOException which should not occur
            System.out.println("Can't save! " + e.toString());
        }
    }

    /**
     * Loads tasks.
     *
     * @return The loaded list, it will be empty if there is a read failure.
     */
    public static ArrayList<Task> loadTasks() throws DukeException {
        try {
            FileInputStream fs = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fs);
            @SuppressWarnings("unchecked")
            ArrayList<Task> res = (ArrayList<Task>) ois.readObject();
            ois.close();
            return res;
        } catch (Exception e) {
            // Either FileNotFoundException or IOException which should not occur
            DukeException de = new DukeException("Can't load file!");
            de.initCause(e);
            throw de;
        }
    }
}

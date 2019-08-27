import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Storage {
    private static String path;

    public static void setPath(String path){
        Storage.path = path;
    }

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

    public static ArrayList<Task> loadTasks() throws DukeException {
        try {
            FileInputStream fs = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fs);
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

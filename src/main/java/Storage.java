import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Storage {


    /**
     * Saves TaskList into drive.
     *
     * @param toSave is the TaskList to be saved
     * @throws IOException if file cannot be written in drive
     */
    public static void save(TaskList toSave) throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/notes.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(toSave);
        oos.close();
    }

    /**
     * Loads TaskList from drive.
     *
     * @return TaskList from drive
     * @throws IOException  file cannot be found
     * @throws ClassNotFoundException  if object to be loaded cannot be founf
     */
    public static TaskList load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/notes.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TaskList toLoad = (TaskList) ois.readObject();
        ois.close();
        return toLoad;

    }

}

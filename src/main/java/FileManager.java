import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/** This class is in charge of recording the current task list to the hard disk as well as reloading,
 * It saves the task list to the file dukedata/datafile.txt,
 * In this file, each line is a task.*/

public class FileManager {

    private BufferedOutputStream recorder = null;
    private BufferedInputStream reader = null;
    private ObjectOutputStream outAssistant = null;
    private ObjectInputStream inAssistant = null;

    FileManager() {}

    /** This method rewrite the string into the taskfile.txt*/
    void rewrite(ArrayList<Task> tasklist) {
        try {
            recorder = new BufferedOutputStream(new FileOutputStream("dukedata/taskfile"));
            outAssistant = new ObjectOutputStream(recorder);
            outAssistant.writeObject(tasklist);
            outAssistant.close();
            recorder.close();
        } catch (IOException e) {
            System.out.println("Records this time failed to be serialized");
        }
    }

    /** This method reloads the previous task list to the current execute.
     * Remember this method will and only will be run once each time the program is started.*/
    ArrayList<Task> reload() {
        ArrayList<Task> toReturn = new ArrayList<>();
        try {
            reader = new BufferedInputStream(new FileInputStream("dukedata/taskfile"));
            inAssistant = new ObjectInputStream(reader);
            toReturn = (ArrayList<Task>) inAssistant.readObject();
            inAssistant.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Previous task list cannot be loaded. (FileNotFoundException)");
        } catch (ClassNotFoundException e) {
            System.out.println("Previous task list cannot be loaded. (ClassNotFoundException)");
        } catch (IOException e) {
            System.out.println("Previous task list cannot be loaded. (IOException)");
        }
        return toReturn;
    }
}

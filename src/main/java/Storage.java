import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Overwrites entire file with current items in Arr, using '***' as a separator.
     */
    public void save(ArrayList<Task> arr) {
        //First delete the old file
        File file = new File(filepath);
        if (file.exists()) file.delete();

        //Write the objects to file
        try (FileOutputStream fout = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            for (Task task : arr) {
                oos.writeObject(task);
            }
        } catch (IOException e) {
            System.err.println("I could not save your updated list to my file :( Please try again.");
        }
    }

    /**
     * Returns an arraylist of Tasks.
     *
     * @return an empty ArrayList if file has not been created or is empty, and the ArrayList of existing Tasks otherwise.
     */
    public ArrayList<Task> load() {
        return null;
    }

}
